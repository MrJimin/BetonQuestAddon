package com.github.mrjimin.betonquestaddon.command

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.util.toMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor

class ReloadCommand(private val plugin: BetonQuestAddonPlugin) {

    fun build(): CommandAPICommand {
        return CommandAPICommand("reload")
            .withAliases("rl")
            .withPermission("betonquestaddon.command.reload")
            .executes(CommandExecutor { sender, _ ->
                plugin.saveDefaultConfig()
                plugin.reloadConfig()
                sender.sendMessage("<gray>BetonQuestAddon</gray> <green>Reloaded successfully!".toMiniMessage())
            })
    }
}
