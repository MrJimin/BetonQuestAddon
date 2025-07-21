package com.github.mrjimin.betonquestaddon.listener

import com.github.mrjimin.betonquestaddon.config.Settings
import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import com.nexomc.nexo.api.events.NexoItemsLoadedEvent
import org.betonquest.betonquest.BetonQuest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NxReloadListener : Listener {

    init {
        if (!"Nexo".checkPlugin()) throw NotFoundPlugin("Nexo")
    }

    @EventHandler
    fun NexoItemsLoadedEvent.onNxReload() {
        if (!Settings.AUTO_RELOAD.toBoolean()) return
        BetonQuest.getInstance().reload()
    }
}