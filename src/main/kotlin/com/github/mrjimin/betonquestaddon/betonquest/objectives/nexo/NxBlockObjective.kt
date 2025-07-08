package com.github.mrjimin.betonquestaddon.betonquest.objectives.nexo

import com.github.mrjimin.betonquestaddon.betonquest.objectives.AbstractBlockObjective
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable

open class NxBlockObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    message: String,
    log: BetonQuestLogger,
    itemID: Variable<String>
) : AbstractBlockObjective<String>(instruction, targetAmount, message, log, itemID) {

    override fun matches(expected: String, inputId: String?): Boolean {
        return expected == inputId
    }
}