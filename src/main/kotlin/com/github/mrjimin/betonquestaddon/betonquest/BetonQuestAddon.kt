package com.github.mrjimin.betonquestaddon.betonquest

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.betonquest.conditions.itemsadder.IaBlockConditionFactory
import com.github.mrjimin.betonquestaddon.betonquest.conditions.nexo.NxBlockConditionFactory
import com.github.mrjimin.betonquestaddon.betonquest.events.itemsadder.IaPlayAnimationEventFactory
import com.github.mrjimin.betonquestaddon.betonquest.events.itemsadder.IaSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.betonquest.events.nexo.NxSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.betonquest.item.itemsadder.IaItemFactory
import com.github.mrjimin.betonquestaddon.betonquest.item.itemsadder.IaItemSerializer
import com.github.mrjimin.betonquestaddon.betonquest.item.nexo.NxItemFactory
import com.github.mrjimin.betonquestaddon.betonquest.item.nexo.NxItemSerializer
import com.github.mrjimin.betonquestaddon.betonquest.objectives.itemsadder.IaBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.betonquest.objectives.itemsadder.IaBlockPlaceObjectiveFactory
import com.github.mrjimin.betonquestaddon.betonquest.objectives.nexo.NxBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.betonquest.objectives.nexo.NxBlockPlaceObjectiveFactory
import com.github.mrjimin.betonquestaddon.listener.IaReloadListener
import com.github.mrjimin.betonquestaddon.listener.NxReloadListener
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.item.QuestItemSerializer
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.inventory.ItemFactory
import org.bukkit.inventory.ItemStack
import team.unnamed.creative.serialize.minecraft.item.ItemSerializer

class BetonQuestAddon(
    private val plugin: BetonQuestAddonPlugin
) {

    private val betonQuest = BetonQuest.getInstance()
    private val loggerFactory = betonQuest.loggerFactory
    private val server: Server = betonQuest.server
    private val data = PrimaryServerThreadData(server, server.scheduler, betonQuest)

    fun initialize() {
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
        if (plugin.config.getBoolean(CONFIG_AUTO_RELOAD)) plugin.logger.info("Auto Reload has been enabled.")

        if ("Nexo".checkPlugin()) {
            server.pluginManager.registerEvents(NxReloadListener(plugin), plugin)
        }
        if ("ItemsAdder".checkPlugin()) {
            server.pluginManager.registerEvents(IaReloadListener(plugin), plugin)
        }
    }

    private fun loadNexo() {
        val registries = betonQuest.questRegistries

        registerItem("nexo", NxItemFactory(), NxItemSerializer())
        registries.condition().registerCombined("nxBlockAt", NxBlockConditionFactory(data))
        registries.event().register("nxBlockAt", NxSetBlockAtEventFactory(data))
        registries.objective().apply {
            register("nxBlockBreak", NxBlockBreakObjectiveFactory(loggerFactory))
            register("nxBlockPlace", NxBlockPlaceObjectiveFactory(loggerFactory))
        }
    }

    private fun loadItemsAdder() {
        val registries = betonQuest.questRegistries

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

    companion object {
        val CONFIG_AUTO_RELOAD = "setting.auto-reload"
    }
}
