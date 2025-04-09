package gg.tater.players.model

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.*

object PlayerTable : Table("players") {
    val uuid: Column<UUID> = uuid("uuid").uniqueIndex()
    val name: Column<String> = varchar("name", 16)
    override val primaryKey = PrimaryKey(uuid)
}