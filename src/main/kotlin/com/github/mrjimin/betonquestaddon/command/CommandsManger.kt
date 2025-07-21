package com.github.mrjimin.betonquestaddon.command

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.util.toMiniMessage
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.command.ConsoleCommandSender

class CommandsManger(private val plugin: BetonQuestAddonPlugin) {

    fun loadsCommands() {
        CommandAPICommand("betonquestaddon")
            .withAliases("bqa", "bqaddon")
            .withPermission("betonquestaddon.command")
            .withSubcommand(ReloadCommand(plugin).build())
            .executes(CommandExecutor { sender, _ ->
                listOf(
                    "<green>${plugin.pluginMeta.name} Info</green>",
                    "<gray>┃ Version:</gray> <white>${plugin.pluginMeta.version}",
                    "<gray>┃ Author:</gray> <gold>${plugin.pluginMeta.authors.joinToString()}",
                    if (sender is ConsoleCommandSender) "<gray>┃ Website:</gray> <blue>${plugin.pluginMeta.website}</blue>"
                    else "<gray>┃ Website:</gray> <click:open_url:'${plugin.pluginMeta.website}'><u><blue>Spigotmc</blue></u></click>"
                ).map(String::toMiniMessage).forEach(sender::sendMessage)
            })
            .register()
    }
}