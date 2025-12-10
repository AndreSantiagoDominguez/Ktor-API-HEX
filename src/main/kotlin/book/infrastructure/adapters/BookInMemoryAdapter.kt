package book.infrastructure.adapters

import book.application.repositories.IBookRepository
import book.domain.Book
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

class BookInMemoryAdapter : IBookRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)

    private val books = mutableMapOf(
        "1" to Book("1", "Cien años de soledad", "1", 1967, "Realismo mágico"),
        "2" to Book("2", "El Aleph", "2", 1949, "Cuentos"),
        "3" to Book("3", "El laberinto de la soledad", "3", 1950, "Ensayo"),
        "4" to Book("4", "Ficciones", "2", 1944, "Cuentos")
    )

    override suspend fun findAll(): List<Book> {
        delay(100)
        logger.info("[REPO] findAll books en hilo: ${Thread.currentThread().name}")
        return books.values.toList()
    }

    override suspend fun findById(id: String): Book? {
        delay(50)
        logger.info("[REPO] findById($id) en hilo: ${Thread.currentThread().name}")
        return books[id]
    }

    override suspend fun save(book: Book): Book {
        delay(80)
        logger.info("[REPO] save book en hilo: ${Thread.currentThread().name}")
        books[book.id] = book
        return book
    }
}
