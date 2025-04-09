package gg.tater.core.api.player

import gg.tater.core.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Player(
    @Serializable(with = UUIDSerializer::class)
    val uuid: UUID,
    var name: String
)