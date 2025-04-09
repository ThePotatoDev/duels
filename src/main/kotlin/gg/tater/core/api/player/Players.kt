package gg.tater.core.api.player

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import java.util.*

private val client = HttpClient(CIO) {
    install(ContentNegotiation) { json() }
}

private const val BASE_URL = "http://localhost:8080"

suspend fun getPlayer(id: UUID): Player {
    return client.get("$BASE_URL/players/$id").body()
}

suspend fun savePlayer(player: Player): HttpResponse {
    return client.post("$BASE_URL/players") {
        contentType(ContentType.Application.Json)
        setBody(player)
    }
}