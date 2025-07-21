package com.github.mrjimin.betonquestaddon

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.command.CommandsManger
import com.github.mrjimin.betonquestaddon.spigot.UpdateChecker
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.plugin.java.JavaPlugin

@Suppress("UnstableApiUsage")
class BetonQuestAddonPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: BetonQuestAddonPlugin
            private set
    }

    override fun onLoad() {
        instance = this
        CommandAPI.onLoad(CommandAPIBukkitConfig(this).verboseOutput(true))
    }

    override fun onEnable() {
        CommandAPI.onEnable()
        Metrics(this, 26421)
        BetonQuestAddon(this).initialize()
        UpdateChecker.checkForUpdates(this, 120813)
        CommandsManger(this).loadsCommands()
        saveDefaultConfig()

        logger.info("BetonQuestAddon v${pluginMeta.version} successfully enabled.")
    }

    override fun onDisable() = CommandAPI.onDisable()

}