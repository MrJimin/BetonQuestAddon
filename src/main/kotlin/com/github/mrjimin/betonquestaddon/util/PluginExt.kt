package com.github.mrjimin.betonquestaddon.util

import org.bukkit.Bukkit

fun String.checkPlugin(): Boolean = Bukkit.getPluginManager().getPlugin(this) != null