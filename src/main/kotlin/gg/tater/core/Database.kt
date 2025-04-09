package gg.tater.core

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

class DatabaseFactory(
    private val host: String,
    private val port: Int,
    private val database: String,
    private val username: String,
    private val password: String
) {
    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://$host:$port/$database"
            driverClassName = "org.postgresql.Driver"
            username = username
            password = password
            maximumPoolSize = 10
        }
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
    }
}
