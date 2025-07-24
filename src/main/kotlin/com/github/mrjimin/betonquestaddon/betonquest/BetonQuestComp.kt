package com.github.mrjimin.betonquestaddon.betonquest

import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.config.PluginMessage
import org.betonquest.betonquest.conversation.ConversationColors
import org.betonquest.betonquest.kernel.processor.quest.VariableProcessor
import org.betonquest.betonquest.kernel.registry.feature.FeatureRegistries
import org.betonquest.betonquest.kernel.registry.quest.QuestTypeRegistries
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.bukkit.Server

object BetonQuestComp {

    val instance: BetonQuest by lazy { BetonQuest.getInstance() }

    val loggerFactory: BetonQuestLoggerFactory
        get() = instance.loggerFactory

    val server: Server
        get() = instance.server

    val pluginMessage: PluginMessage
        get() = instance.pluginMessage

    val variableProcessor: VariableProcessor
        get() = instance.variableProcessor

    val conversationColors: ConversationColors
        get() = instance.conversationColors

    val featureRegistries: FeatureRegistries
        get() = instance.featureRegistries

    val questRegistries: QuestTypeRegistries
        get() = instance.questRegistries

    val data: PrimaryServerThreadData by lazy { PrimaryServerThreadData(server, server.scheduler, instance) }

}
