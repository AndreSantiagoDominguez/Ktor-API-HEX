package book.infrastructure.controllers

import book.application.GetAllBooksUseCase
import book.domain.Book
import org.slf4j.LoggerFactory

class GetAllBooksController(private val useCase: GetAllBooksUseCase) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun handle(): List<Book> {
        logger.info("Petición recibida en hilo: ${Thread.currentThread().name}")
        return useCase.execute()
    }
}
