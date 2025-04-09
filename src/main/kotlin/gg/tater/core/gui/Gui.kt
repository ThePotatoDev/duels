package gg.tater.core.gui

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

abstract class Gui(
    private val player: Player,
    private val title: Component,
    private val lines: Int,
    var onClose: ((Player) -> Unit)? = null
) : InventoryHolder {

    private val inv: Inventory? = null

    override fun getInventory(): Inventory {
        if (inv == null) {
            return Bukkit.createInventory(this, lines * 9, title)
        }

        return inv
    }

    private val buttonMap: MutableMap<Int, Button> = mutableMapOf()

    abstract fun draw()

    fun setButton(slot: Int, button: Button) {
        buttonMap[slot] = button
        inventory.setItem(slot, button.item.toBukkitStack())
    }

    fun removeButton(slot: Int) {
        val button = buttonMap.remove(slot) ?: return
        inventory.remove(button.item.toBukkitStack())
    }

    fun getButton(slot: Int): Button? {
        return buttonMap[slot]
    }

    fun close() {
        player.closeInventory(InventoryCloseEvent.Reason.PLUGIN)
    }

    fun open() {
        if (inv != null) {
            player.openInventory(inv)
        }
    }
}