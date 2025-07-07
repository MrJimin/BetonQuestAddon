package com.github.mrjimin.betonquestadder

import com.github.mrjimin.betonquestadder.item.ItemFactory
import com.github.mrjimin.betonquestadder.item.ItemSerializer
import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.bukkit.Server
import org.bukkit.plugin.java.JavaPlugin


class BetonQuestAdderPlugin : JavaPlugin() {

    override fun onEnable() {
        Metrics(this, 26415)

        val betonQuest = BetonQuest.getInstance()
//        val loggerFactory = betonQuest.loggerFactory
//        val server: Server = betonQuest.server
//        val data = PrimaryServerThreadData(server, server.scheduler, betonQuest)
//        val questRegistries = betonQuest.questRegistries

        // val condition = questRegistries.condition()
        val item = betonQuest.featureRegistries.item()
        item.register("nexo", ItemFactory())
        item.registerSerializer("nexo", ItemSerializer())
    }
}