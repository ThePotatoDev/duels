package gg.tater.players

import gg.tater.core.DatabaseFactory
import gg.tater.core.api.player.Player
import gg.tater.players.model.Players
import io.github.cdimascio.dotenv.Dotenv
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

class PlayersServiceApplication {

    fun main() {
        val env = Dotenv.load()

        embeddedServer(Netty, port = 8080) {
            install(ContentNegotiation) {
                json()
            }

            DatabaseFactory(
                env.get("SQL_HOST"),
                env.get("SQL_PORT").toInt(),
                env.get("SQL_DATABASE"),
                env.get("SQL_USERNAME"),
                env.get("SQL_PASSWORD")
            ).init()

            Players.createTable()

            routing {
                post("/players") {
                    val player = call.receive<Player>()
                    Players.save(player)
                    call.respondText("Player saved: ${player.name}", status = HttpStatusCode.Created)
                }

                get("/players/{uuid}") {
                    val param = call.parameters["uuid"]

                    if (param == null) {
                        call.respond(HttpStatusCode.BadRequest, "Missing UUID")
                        return@get
                    }

                    val uuid = runCatching { UUID.fromString(param) }.getOrNull()

                    if (uuid == null) {
                        call.respond(HttpStatusCode.BadRequest, "Invalid UUID format")
                        return@get
                    }

                    val player = Players.get(uuid)

                    if (player == null) {
                        call.respond(HttpStatusCode.NotFound, "Player not found")
                    } else {
                        call.respond(player)
                    }
                }
            }
        }.start(wait = true)
    }
}