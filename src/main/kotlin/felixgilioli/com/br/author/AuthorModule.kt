package felixgilioli.com.br.author

import felixgilioli.com.br.core.Response
import felixgilioli.com.br.core.connection
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.dsl.*

fun Application.authorModule() {

    routing {
        get("/author") {
            val authorList = connection.from(AuthorEntity)
                .select()
                .map {
                    AuthorDto(
                        it[AuthorEntity.id]?.toLong(),
                        it[AuthorEntity.firstName].toString(),
                        it[AuthorEntity.lastName].toString()
                    )
                }

            if (authorList.isEmpty()) {
                call.respond(
                    HttpStatusCode.NotFound, Response(
                        success = false,
                        data = "No authors found"
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.OK, Response(
                        success = true,
                        data = authorList
                    )
                )
            }
        }

        get("/author/{id}") {
            val authorId = call.parameters["id"]?.toInt() ?: -1
            val author = connection.from(AuthorEntity)
                .select()
                .where { AuthorEntity.id eq authorId }
                .map {
                    AuthorDto(
                        it[AuthorEntity.id]?.toLong(),
                        it[AuthorEntity.firstName].toString(),
                        it[AuthorEntity.lastName].toString()
                    )
                }
                .firstOrNull()

            if (author == null) {
                call.respond(
                    HttpStatusCode.NotFound, Response(
                        success = false,
                        data = "Could not found author with id = $authorId"
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.OK, Response(
                        success = true,
                        data = author
                    )
                )
            }
        }

        post("/author") {
            val author = call.receive<AuthorDto>()
            val result = connection.insert(AuthorEntity) {
                set(it.firstName, author.firstName)
                set(it.lastName, author.lastName)
            }

            if (result == 1) {
                call.respond(
                    HttpStatusCode.Created, Response(
                        success = true,
                        data = "Values has been successfully inserted"
                    )
                )
            } else {
                call.respond(
                    HttpStatusCode.BadRequest, Response(
                        success = false,
                        data = "Failed to insert values"
                    )
                )
            }
        }
    }
}