package book.infrastructure.controllers

import book.application.GetBookByIdUseCase
import book.domain.Book
import org.slf4j.LoggerFactory

class GetBookByIdController(private val useCase: GetBookByIdUseCase) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun handle(id: String): Book? {
        logger.info("Petición recibida para id=$id en hilo: ${Thread.currentThread().name}")
        return useCase.execute(id)
    }
}
