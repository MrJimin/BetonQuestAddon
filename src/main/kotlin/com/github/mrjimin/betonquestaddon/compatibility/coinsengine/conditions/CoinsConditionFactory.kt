package com.github.mrjimin.betonquestaddon.compatibility.coinsengine.conditions

import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.condition.PrimaryServerThreadPlayerCondition

class CoinsConditionFactory(
    private val data: PrimaryServerThreadData
) : PlayerConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val currencyId = instruction.next()
        val amount: Variable<Number> = instruction.get(Argument.NUMBER_NOT_LESS_THAN_ONE)
        return PrimaryServerThreadPlayerCondition(CoinsCondition(currencyId, amount), data)
    }
}
