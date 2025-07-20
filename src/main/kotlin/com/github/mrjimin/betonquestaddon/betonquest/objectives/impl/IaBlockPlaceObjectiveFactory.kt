package com.github.mrjimin.betonquestaddon.betonquest.objectives.impl

import com.github.mrjimin.betonquestaddon.betonquest.objectives.IaBlockObjective
import com.github.mrjimin.betonquestaddon.betonquest.parser.IaParser
import dev.lone.itemsadder.api.CustomStack
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority

class IaBlockPlaceObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective? {
        val itemID = instruction.get(IaParser.Companion.PARSER)
        val targetAmount = instruction.getValue("amount", Argument.NUMBER_NOT_LESS_THAN_ONE, 1)!!
        val log = loggerFactory.create(IaBlockObjective::class.java)
        return IaBlockPlace(instruction, targetAmount, log, itemID)
    }
}

class IaBlockPlace(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<CustomStack>
) : IaBlockObjective(instruction, targetAmount, "blocks_to_place", log, itemID) {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun CustomBlockPlaceEvent.onBlockPlace() {
        handle(namespacedID, player)
    }
}