package com.github.mrjimin.betonquestaddon

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import org.bukkit.plugin.java.JavaPlugin

@Suppress("UnstableApiUsage")
class BetonQuestAddonPlugin : JavaPlugin() {

    override fun onEnable() {
        Metrics(this, 26421)
        BetonQuestAddon(this).initialize()
        saveDefaultConfig()

        logger.info("BetonQuestAddon v${pluginMeta.version} successfully enabled.")
    }

}