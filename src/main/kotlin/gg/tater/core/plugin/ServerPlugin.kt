package gg.tater.core.plugin

import gg.tater.core.gui.Gui
import gg.tater.core.listen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

abstract class ServerPlugin : JavaPlugin(), CoroutineScope by CoroutineScope(Dispatchers.Default) {

    companion object {
        // This should not be referenced for anything unless necessary for Bukkit
        // targeted implementations
        var INSTANCE: Plugin? = null
    }

    override fun onEnable() {
        INSTANCE = this

        super.onEnable()

        listen<InventoryOpenEvent>(this) {
            if (inventory.holder !is Gui) return@listen
            val gui = inventory.holder as Gui
            gui.draw()
        }

        listen<InventoryClickEvent>(this) {
            if (clickedInventory == null
                || clickedInventory!!.holder == null
                || clickedInventory!!.holder !is Gui
                || whoClicked !is Player
            ) return@listen

            val player = whoClicked as Player
            val gui = clickedInventory!!.holder as Gui
            val slot = rawSlot
            val button = gui.getButton(slot) ?: return@listen

            this.result = Event.Result.DENY

            if (button.onClick == null) return@listen
            button.onClick!!.invoke(player)
        }

        listen<InventoryCloseEvent>(this) {
            if (inventory.holder !is Gui || player !is Player) return@listen
            val gui = inventory.holder as Gui
            if (gui.onClose == null) return@listen
            gui.onClose!!.invoke(player as Player)
        }
    }
}