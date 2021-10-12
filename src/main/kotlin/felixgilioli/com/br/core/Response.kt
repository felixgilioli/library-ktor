package felixgilioli.com.br.core

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val data: T,
    val success: Boolean
)
