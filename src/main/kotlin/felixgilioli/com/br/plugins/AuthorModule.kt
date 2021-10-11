package felixgilioli.com.br.plugins

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable

fun Application.authorModule() {

    routing {
        get("/author") {
            call.respondText("author doaskpdas")
        }

        get("/author/{id}") {
            val authorId = call.parameters["id"]
            println("the id of author is: $authorId")
            call.respondText("ok")
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
    val firstName: String,
    val lastName: String
)