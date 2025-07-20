package com.github.mrjimin.betonquestaddon.listener

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import dev.lone.itemsadder.api.Events.ItemsAdderFirstLoadEvent
import org.betonquest.betonquest.BetonQuest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class IaReloadListener(
    private val plugin: BetonQuestAddonPlugin
) : Listener {

    init {
        if (!"ItemsAdder".checkPlugin()) throw NotFoundPlugin("ItemsAdder")
    }

    @EventHandler
    fun ItemsAdderFirstLoadEvent.onIaReload() {
        if (!plugin.config.getBoolean(BetonQuestAddon.CONFIG_AUTO_RELOAD)) return
        BetonQuest.getInstance().reload()
    }
}