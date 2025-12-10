package author.domain

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val id: String,
    val name: String,
    val nationality: String,
    val birthYear: Int
)
