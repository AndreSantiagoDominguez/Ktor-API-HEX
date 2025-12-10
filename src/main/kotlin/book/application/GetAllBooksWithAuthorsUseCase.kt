package book.application

import author.application.repositories.IAuthorRepository
import book.application.repositories.IBookRepository
import book.domain.BookWithAuthor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class GetAllBooksWithAuthorsUseCase(
    private val bookRepository: IBookRepository,
    private val authorRepository: IAuthorRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(): List<BookWithAuthor> = coroutineScope {
        logger.info("Iniciando búsqueda en hilo: ${Thread.currentThread().name}")

        val books = withContext(Dispatchers.IO) {
            bookRepository.findAll()
        }

        // Búsqueda de autores en PARALELO usando async
        val booksWithAuthors = books.map { book ->
            async(Dispatchers.IO) {
                logger.info("Buscando autor ${book.authorId} para '${book.title}' en hilo: ${Thread.currentThread().name}")
                val author = authorRepository.findById(book.authorId)
                BookWithAuthor(book, author)
            }
        }.awaitAll()

        logger.info("Completado: ${booksWithAuthors.size} libros procesados en paralelo")
        booksWithAuthors
    }
}
