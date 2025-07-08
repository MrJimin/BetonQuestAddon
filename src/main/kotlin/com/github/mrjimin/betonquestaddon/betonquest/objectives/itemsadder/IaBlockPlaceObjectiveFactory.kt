package com.github.mrjimin.betonquestaddon.betonquest.objectives.itemsadder

import com.github.mrjimin.betonquestaddon.betonquest.parser.IaParser
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument

class IaBlockPlaceObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective? {
        val itemID = instruction.get(IaParser.PARSER)
        val targetAmount = instruction.getValue("amount", Argument.NUMBER_NOT_LESS_THAN_ONE, 1)!!
        val log = loggerFactory.create(IaBlockObjective::class.java)
        return IaBlockPlace(instruction, targetAmount, log, itemID)
    }
}