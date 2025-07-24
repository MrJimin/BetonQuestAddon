package com.github.mrjimin.betonquestaddon.betonquest

import com.github.mrjimin.betonquestaddon.betonquest.objectives.chat.ChatObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegratorHandler

object BetonQuestAddon {

    fun initialize() {
        BQAddonIntegratorHandler(
            BetonQuestComp.loggerFactory.create(BetonQuestAddon::class.java),
            BetonQuestComp.data,
            BetonQuestComp.pluginMessage,
            BetonQuestComp.variableProcessor,
            BetonQuestComp.questRegistries
        )

        loadConditions()
        loadEvents()
        loadObjectives()
    }

    private fun loadConditions() {
    }

    private fun loadEvents() {
    }

    private fun loadObjectives() {
        BetonQuestComp.questRegistries.objective.register("chat", ChatObjectiveFactory)
    }

}
