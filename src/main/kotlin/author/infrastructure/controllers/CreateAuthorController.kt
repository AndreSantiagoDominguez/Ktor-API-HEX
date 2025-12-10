package author.infrastructure.controllers

import author.application.CreateAuthorUseCase
import author.domain.Author
import org.slf4j.LoggerFactory

class CreateAuthorController(private val useCase: CreateAuthorUseCase) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun handle(author: Author): Author {
        logger.info("Petición POST recibida en hilo: ${Thread.currentThread().name}")
        return useCase.execute(author)
    }
}
