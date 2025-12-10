package author.application

import author.application.repositories.IAuthorRepository
import author.domain.Author
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class CreateAuthorUseCase(private val repository: IAuthorRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun execute(author: Author): Author = withContext(Dispatchers.IO) {
        logger.info("Guardando autor en hilo: ${Thread.currentThread().name}")
        repository.save(author)
    }
}
