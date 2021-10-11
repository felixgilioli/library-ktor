package felixgilioli.com.br

import felixgilioli.com.br.entity.AuthorEntity
import felixgilioli.com.br.routing.authorModule
import felixgilioli.com.br.routing.configureRouting
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.ktorm.database.Database
import org.ktorm.dsl.insert

fun main() {
    embeddedServer(Netty, port = 8181, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json()
        }
        configureRouting()
        authorModule()

        val connection = Database.connect(
            url = "jdbc:postgresql://localhost:5432/library",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "admin"
        )

        connection.insert(AuthorEntity) {
            set(it.firstName, "Felix")
            set(it.lastName, "Gilioli")
        }
    }.start(wait = true)
}
