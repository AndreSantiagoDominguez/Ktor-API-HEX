package author.infrastructure.adapters

import author.application.repositories.IAuthorRepository
import author.domain.Author
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

class AuthorInMemoryAdapter : IAuthorRepository {

    private val logger = LoggerFactory.getLogger(this::class.java)

    private val authors = mutableMapOf(
        "1" to Author("1", "Gabriel García Márquez", "Colombiano", 1927),
        "2" to Author("2", "Jorge Luis Borges", "Argentino", 1899),
        "3" to Author("3", "Octavio Paz", "Mexicano", 1914)
    )

    override suspend fun findAll(): List<Author> {
        delay(100) // Simula latencia
        logger.info("[REPO] findAll en hilo: ${Thread.currentThread().name}")
        return authors.values.toList()
    }

    override suspend fun findById(id: String): Author? {
        delay(50)
        logger.info("[REPO] findById($id) en hilo: ${Thread.currentThread().name}")
        return authors[id]
    }

    override suspend fun save(author: Author): Author {
        delay(80)
        logger.info("[REPO] save en hilo: ${Thread.currentThread().name}")
        authors[author.id] = author
        return author
    }
}
