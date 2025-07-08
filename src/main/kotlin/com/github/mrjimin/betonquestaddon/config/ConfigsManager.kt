package com.github.mrjimin.betonquestaddon.config

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin

class ConfigsManager(
    private val plugin: BetonQuestAddonPlugin
) {

    fun initialize() {
        plugin.saveDefaultConfig()
        plugin.saveConfig()
    }

    fun getAutoReload() : Boolean = plugin.config.getBoolean("setting.auto-reload")

}