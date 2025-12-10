package author.infrastructure.controllers

import author.application.GetAuthorByIdUseCase
import author.domain.Author
import org.slf4j.LoggerFactory

class GetAuthorByIdController(private val useCase: GetAuthorByIdUseCase) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun handle(id: String): Author? {
        logger.info("Petición recibida para id=$id en hilo: ${Thread.currentThread().name}")
        return useCase.execute(id)
    }
}
