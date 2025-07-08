package com.github.mrjimin.betonquestaddon.betonquest.objectives.itemsadder

import com.github.mrjimin.betonquestaddon.betonquest.objectives.AbstractBlockObjective
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable

open class IaBlockObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    message: String,
    log: BetonQuestLogger,
    itemID: Variable<CustomStack>
) : AbstractBlockObjective<CustomStack>(instruction, targetAmount, message, log, itemID) {

    override fun matches(expected: CustomStack, inputId: String?): Boolean {
        return expected.namespacedID == inputId
    }
}