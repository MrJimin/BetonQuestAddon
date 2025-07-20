package com.github.mrjimin.betonquestaddon.betonquest.objectives.impl

import com.github.mrjimin.betonquestaddon.betonquest.objectives.NxBlockObjective
import com.github.mrjimin.betonquestaddon.betonquest.parser.NxParser
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class NxBlockPlaceObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(NxParser.PARSER)
        val targetAmount = instruction.getValue("amount", Argument.NUMBER_NOT_LESS_THAN_ONE, 1)!!
        val log = loggerFactory.create(NxBlockObjective::class.java)
        return NxBlockPlace(instruction, targetAmount, log, itemID)
    }
}

class NxBlockPlace(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<String>
) : NxBlockObjective(instruction, targetAmount, "blocks_to_place", log, itemID), Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun NexoBlockPlaceEvent.onNexoBlockPlace() {
        handle(mechanic.itemID, player)
    }
}