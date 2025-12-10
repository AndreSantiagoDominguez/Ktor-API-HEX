package book.application

import book.application.repositories.IBookRepository
import book.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class CreateBookUseCase(private val repository: IBookRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(book: Book): Book = withContext(Dispatchers.IO) {
        logger.info("Guardando libro en hilo: ${Thread.currentThread().name}")
        repository.save(book)
    }
}
