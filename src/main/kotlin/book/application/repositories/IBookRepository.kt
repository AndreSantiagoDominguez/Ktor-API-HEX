package book.application.repositories

import book.domain.Book

interface IBookRepository {
    suspend fun findAll(): List<Book>
    suspend fun findById(id: String): Book?
    suspend fun save(book: Book): Book
}
