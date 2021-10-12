package felixgilioli.com.br.core

import org.ktorm.database.Database

val connection: Database by lazy {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/library",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "admin"
    )
}