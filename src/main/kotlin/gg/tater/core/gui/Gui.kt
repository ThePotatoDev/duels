package gg.tater.core.gui

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

abstract class Gui(val player: Player, val title: Component, val lines: Int) {
}