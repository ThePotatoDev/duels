package gg.tater.core

import gg.tater.core.plugin.ServerPlugin
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

fun runRepeating(delay: Long, repeat: Long, task: Runnable): BukkitTask {
    return Bukkit.getScheduler().runTaskTimer(ServerPlugin.INSTANCE!!, task, delay, repeat)
}