package author.application.repositories

import author.domain.Author

interface IAuthorRepository {
    suspend fun findAll(): List<Author>
    suspend fun findById(id: String): Author?
    suspend fun save(author: Author): Author
}
