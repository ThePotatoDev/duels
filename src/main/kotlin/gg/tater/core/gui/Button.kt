package gg.tater.core.gui

import gg.tater.core.Item
import org.bukkit.entity.Player

data class Button(val item: Item, var onClick: ((Player) -> Unit)? = null) {

}