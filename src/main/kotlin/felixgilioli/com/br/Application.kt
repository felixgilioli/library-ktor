package felixgilioli.com.br

import felixgilioli.com.br.plugins.authorModule
import felixgilioli.com.br.plugins.configureRouting
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
        configureRouting()
        authorModule()
    }.start(wait = true)
}
