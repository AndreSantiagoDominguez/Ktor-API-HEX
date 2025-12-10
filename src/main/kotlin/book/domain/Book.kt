package book.domain

import author.domain.Author
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val title: String,
    val authorId: String,
    val year: Int,
    val genre: String
)

@Serializable
data class BookWithAuthor(
    val book: Book,
    val author: Author?
)
