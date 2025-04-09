package gg.tater.core

import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.Plugin

class EventListener(private val listener: Event.() -> Unit) : EventExecutor, Listener {
    override fun execute(ignored: Listener, event: Event) = listener(event)
    fun unregister() = HandlerList.unregisterAll(this)
}

inline fun <reified T : Event> listen(
    plugin: Plugin,
    priority: EventPriority = EventPriority.NORMAL,
    noinline listener: T.() -> Unit
) = EventListener(listener as Event.() -> Unit).also {
    Bukkit.getPluginManager().registerEvent(T::class.java, it, priority, it, plugin)
}