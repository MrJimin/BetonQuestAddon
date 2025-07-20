package com.github.mrjimin.betonquestaddon

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.spigot.UpdateChecker
import org.bukkit.plugin.java.JavaPlugin

@Suppress("UnstableApiUsage")
class BetonQuestAddonPlugin : JavaPlugin() {

    override fun onEnable() {
        Metrics(this, 26421)
        BetonQuestAddon(this).initialize()
        UpdateChecker.checkForUpdates(this, 120813)
        saveDefaultConfig()

        logger.info("BetonQuestAddon v${pluginMeta.version} successfully enabled.")
    }

}