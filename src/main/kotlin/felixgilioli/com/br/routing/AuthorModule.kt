package felixgilioli.com.br.routing

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable

fun Application.authorModule() {

    routing {
        get("/author") {
            val author1 = Author(1,"Felix", "Gilioli")
            val author2 = Author(2,"Daiane", "Bellon")
            call.respond(listOf(author1, author2))
        }

        get("/author/{id}") {
            val authorId = call.parameters["id"]
            println("the id of author is: $authorId")
            val author = Author(authorId?.toLong(),"Felix", "Gilioli")
            call.respond(author)
        }

        post("/author") {
            val author = call.receive<Author>()
            println(author)
            call.respondText("author saved")
        }
    }
}

@Serializable
data class Author(
    val id: Long? = null,
    val firstName: String,
    val lastName: String
)