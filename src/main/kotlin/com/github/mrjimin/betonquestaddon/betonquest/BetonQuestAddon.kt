package com.github.mrjimin.betonquestaddon.betonquest

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.betonquest.conditions.IaBlockConditionFactory
import com.github.mrjimin.betonquestaddon.betonquest.conditions.NxBlockConditionFactory
import com.github.mrjimin.betonquestaddon.betonquest.events.IaPlayAnimationEventFactory
import com.github.mrjimin.betonquestaddon.betonquest.events.IaSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.betonquest.events.NxSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.betonquest.item.IaItemFactory
import com.github.mrjimin.betonquestaddon.betonquest.item.IaItemSerializer
import com.github.mrjimin.betonquestaddon.betonquest.item.NxItemFactory
import com.github.mrjimin.betonquestaddon.betonquest.item.NxItemSerializer
import com.github.mrjimin.betonquestaddon.betonquest.objectives.ChatObjectiveFactory
import com.github.mrjimin.betonquestaddon.betonquest.objectives.impl.IaBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.betonquest.objectives.impl.IaBlockPlaceObjectiveFactory
import com.github.mrjimin.betonquestaddon.betonquest.objectives.impl.NxBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.betonquest.objectives.impl.NxBlockPlaceObjectiveFactory
import com.github.mrjimin.betonquestaddon.config.Settings
import com.github.mrjimin.betonquestaddon.listener.IaReloadListener
import com.github.mrjimin.betonquestaddon.listener.NxReloadListener
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.item.QuestItemSerializer
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.bukkit.Server

class BetonQuestAddon(
    private val plugin: BetonQuestAddonPlugin
) {

    private val betonQuest = BetonQuest.getInstance()
    private val loggerFactory = betonQuest.loggerFactory
    private val server: Server = betonQuest.server
    private val data = PrimaryServerThreadData(server, server.scheduler, betonQuest)
    private val registries = betonQuest.questRegistries

    fun initialize() {
        loadCustomPlugin()
        loadObjective()
    }

    fun loadObjective() {
        registries.objective.register("chat",ChatObjectiveFactory)
    }

    fun loadCustomPlugin() {
        if ("Nexo".checkPlugin()) {
            loadNexo()
            plugin.logger.info("Nexo is initialized")
        }
        if ("ItemsAdder".checkPlugin()) {
            loadItemsAdder()
            plugin.logger.info("ItemsAdder is initialized")
        }
        registerEvents()
    }

    private fun registerEvents() {
        if (Settings.AUTO_RELOAD.toBoolean()) plugin.logger.info("Auto Reload has been enabled.")

        if ("Nexo".checkPlugin()) {
            server.pluginManager.registerEvents(NxReloadListener(), plugin)
        }
        if ("ItemsAdder".checkPlugin()) {
            server.pluginManager.registerEvents(IaReloadListener(), plugin)
        }
    }

    private fun loadNexo() {
        registerItem("nexo", NxItemFactory(), NxItemSerializer())
        registries.condition().registerCombined("nxBlockAt", NxBlockConditionFactory(data))
        registries.event().register("nxBlockAt", NxSetBlockAtEventFactory(data))
        registries.objective().apply {
            register("nxBlockBreak", NxBlockBreakObjectiveFactory(loggerFactory))
            register("nxBlockPlace", NxBlockPlaceObjectiveFactory(loggerFactory))
        }
    }

    private fun loadItemsAdder() {
        registerItem("ia", IaItemFactory(), IaItemSerializer())
        registries.condition().registerCombined("iaBlockAt", IaBlockConditionFactory(data))
        registries.event().apply {
            register("iaBlockAt", IaSetBlockAtEventFactory(data))
            register("iaPlayAnimation", IaPlayAnimationEventFactory(loggerFactory, data))
        }
        registries.objective().apply {
            register("iaBlockBreak", IaBlockBreakObjectiveFactory(loggerFactory))
            register("iaBlockPlace", IaBlockPlaceObjectiveFactory(loggerFactory))
        }
    }


    private fun registerItem(
        name: String,
        factory: TypeFactory<QuestItemWrapper>,
        serializer: QuestItemSerializer
    ) {
        val itemRegistry = betonQuest.featureRegistries.item()
        itemRegistry.register(name, factory)
        itemRegistry.registerSerializer(name, serializer)
    }
}
