package gg.tater.core

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class Item(
    val name: Component,
    val material: Material,
    var amount: Int = 1,
    var lore: List<Component> = emptyList()
) {

    fun toBukkitStack(): ItemStack {
        val stack = ItemStack(material, amount)

        val meta = stack.itemMeta
        meta.displayName(name)
        meta.lore(lore)

        stack.setItemMeta(meta)

        return stack
    }
}