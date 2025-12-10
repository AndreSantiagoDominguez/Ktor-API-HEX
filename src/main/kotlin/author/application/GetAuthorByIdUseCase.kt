package author.application

import author.application.repositories.IAuthorRepository
import author.domain.Author
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class GetAuthorByIdUseCase(private val repository: IAuthorRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(id: String): Author? = withContext(Dispatchers.IO) {
        logger.info("Buscando autor $id en hilo: ${Thread.currentThread().name}")
        repository.findById(id)
    }
}
