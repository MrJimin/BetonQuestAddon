package com.github.mrjimin.betonquestaddon

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import org.bukkit.plugin.java.JavaPlugin

@Suppress("UnstableApiUsage")
class BetonQuestAddonPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: BetonQuestAddonPlugin
            private set
    }

    override fun onEnable() {
        instance = this

        Metrics(this, 26421)
        BetonQuestAddon(this).initialize()
        saveDefaultConfig()

        logger.info("BetonQuestAddon v${pluginMeta.version} successfully enabled.")
    }

}