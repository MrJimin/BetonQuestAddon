package com.github.mrjimin.betonquestaddon.betonquest.conditions

import com.github.mrjimin.betonquestaddon.betonquest.parser.IaParser
import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.PlayerlessCondition
import org.betonquest.betonquest.api.quest.condition.PlayerlessConditionFactory
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.betonquest.betonquest.api.quest.condition.nullable.NullableConditionAdapter
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.condition.PrimaryServerThreadPlayerCondition
import org.betonquest.betonquest.quest.condition.PrimaryServerThreadPlayerlessCondition
import org.bukkit.Location

class IaBlockConditionFactory(
    private val data: PrimaryServerThreadData
) : PlayerConditionFactory, PlayerlessConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        return PrimaryServerThreadPlayerCondition(
            NullableConditionAdapter(parseInstruction(instruction)), data
        )
    }

    override fun parsePlayerless(instruction: Instruction): PlayerlessCondition {
        return PrimaryServerThreadPlayerlessCondition(
            NullableConditionAdapter(parseInstruction(instruction)), data
        )
    }

    private fun parseInstruction(instruction: Instruction): IaBlock {
        val itemID = instruction.get(IaParser.Companion.PARSER)
        val location = instruction.get(Argument.LOCATION)
        return IaBlock(itemID, location)
    }
}

class IaBlock(
    private val itemID: Variable<CustomStack>,
    private val location: Variable<Location?>
) : NullableCondition {

    override fun check(profile: Profile?): Boolean {
        val block = CustomBlock.byAlreadyPlaced(location.getValue(profile)!!.block)
        return block != null && block.customStack.matchNamespacedID(itemID.getValue(profile))
    }
}