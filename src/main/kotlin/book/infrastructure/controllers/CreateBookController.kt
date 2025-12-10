package book.infrastructure.controllers

import book.application.CreateBookUseCase
import book.domain.Book
import org.slf4j.LoggerFactory

class CreateBookController(private val useCase: CreateBookUseCase) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun handle(book: Book): Book {
        logger.info("Petición POST recibida en hilo: ${Thread.currentThread().name}")
        return useCase.execute(book)
    }
}
