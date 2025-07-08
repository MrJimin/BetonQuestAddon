package com.github.mrjimin.betonquestaddon.betonquest.objectives.nexo

import com.github.mrjimin.betonquestaddon.betonquest.parser.NxParser
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument

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

//class NxBlockPlaceObjectiveFactory(loggerFactory: BetonQuestLoggerFactory) : NxBlockObjectiveFactoryBase<NxBlockPlace>(loggerFactory) {
//    override fun create(instruction: Instruction, targetAmount: Variable<Number>, log: BetonQuestLogger, itemID: Variable<String>) =
//        NxBlockPlace(instruction, targetAmount, log, itemID)
//}