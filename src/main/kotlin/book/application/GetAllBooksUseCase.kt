package book.application

import book.application.repositories.IBookRepository
import book.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class GetAllBooksUseCase(private val repository: IBookRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(): List<Book> = withContext(Dispatchers.IO) {
        logger.info("Ejecutando en hilo: ${Thread.currentThread().name}")
        repository.findAll()
    }
}
