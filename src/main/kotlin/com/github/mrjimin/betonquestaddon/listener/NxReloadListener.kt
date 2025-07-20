package com.github.mrjimin.betonquestaddon.listener

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import com.nexomc.nexo.api.events.NexoItemsLoadedEvent
import org.betonquest.betonquest.BetonQuest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NxReloadListener(
    private val plugin: BetonQuestAddonPlugin
) : Listener {

    init {
        if (!"Nexo".checkPlugin()) throw NotFoundPlugin("Nexo")
    }

    @EventHandler
    fun NexoItemsLoadedEvent.onNxReload() {
        if (!plugin.config.getBoolean(BetonQuestAddon.CONFIG_AUTO_RELOAD)) return
        BetonQuest.getInstance().reload()
    }
}