package author.infrastructure.controllers

import author.application.GetAllAuthorsUseCase
import author.domain.Author
import org.slf4j.LoggerFactory

class GetAllAuthorsController(private val useCase: GetAllAuthorsUseCase) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun handle(): List<Author> {
        logger.info("Petición recibida en hilo: ${Thread.currentThread().name}")
        return useCase.execute()
    }
}
