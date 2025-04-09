package gg.tater.players.model

import gg.tater.core.api.player.Player
import gg.tater.players.model.PlayerTable.name
import gg.tater.players.model.PlayerTable.uuid
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object Players {
    fun createTable() = transaction {
        SchemaUtils.create(PlayerTable)
    }

    fun save(player: Player) = transaction {
        PlayerTable.insert {
            it[uuid] = player.uuid
            it[name] = player.name
        }
    }

    fun get(id: UUID): Player? = transaction {
        PlayerTable.select { uuid eq id }
            .singleOrNull()
            ?.let {
                Player(uuid = it[uuid], name = it[name])
            }
    }
}