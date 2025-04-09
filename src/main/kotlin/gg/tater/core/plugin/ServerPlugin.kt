package gg.tater.core.plugin

import gg.tater.core.listen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.plugin.java.JavaPlugin

abstract class ServerPlugin : JavaPlugin(), CoroutineScope by CoroutineScope(Dispatchers.Default) {

    override fun onEnable() {
        super.onEnable()

        listen<InventoryOpenEvent>(this) {

        }

        listen<InventoryClickEvent>(this) {

        }

        listen<InventoryCloseEvent>(this) {

        }
    }
}