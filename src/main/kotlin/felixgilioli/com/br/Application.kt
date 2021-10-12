package felixgilioli.com.br

import felixgilioli.com.br.author.authorModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8181, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json()
        }
        authorModule()
    }.start(wait = true)
}
