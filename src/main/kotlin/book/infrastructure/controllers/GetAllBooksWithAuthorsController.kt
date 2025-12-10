package book.infrastructure.controllers

import book.application.GetAllBooksWithAuthorsUseCase
import book.domain.BookWithAuthor
import org.slf4j.LoggerFactory

class GetAllBooksWithAuthorsController(private val useCase: GetAllBooksWithAuthorsUseCase) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun handle(): List<BookWithAuthor> {
        logger.info("Petición recibida en hilo: ${Thread.currentThread().name}")
        return useCase.execute()
    }
}
