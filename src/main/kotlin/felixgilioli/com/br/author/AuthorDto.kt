package felixgilioli.com.br.author

import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val id: Long? = null,
    val firstName: String,
    val lastName: String
)
