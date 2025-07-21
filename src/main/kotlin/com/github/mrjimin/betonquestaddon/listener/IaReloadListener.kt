package com.github.mrjimin.betonquestaddon.listener

import com.github.mrjimin.betonquestaddon.config.Settings
import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import dev.lone.itemsadder.api.Events.ItemsAdderFirstLoadEvent
import org.betonquest.betonquest.BetonQuest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class IaReloadListener : Listener {

    init {
        if (!"ItemsAdder".checkPlugin()) throw NotFoundPlugin("ItemsAdder")
    }

    @EventHandler
    fun ItemsAdderFirstLoadEvent.onIaReload() {
        if (!Settings.AUTO_RELOAD.toBoolean()) return
        BetonQuest.getInstance().reload()
    }
}