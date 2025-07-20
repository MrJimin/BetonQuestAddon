package com.github.mrjimin.betonquestaddon.betonquest.conditions

import com.github.mrjimin.betonquestaddon.betonquest.parser.NxParser
import com.nexomc.nexo.api.NexoBlocks
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

class NxBlockConditionFactory(
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

    private fun parseInstruction(instruction: Instruction): NxBlock {
        val itemID: Variable<String> = instruction.get(NxParser.Companion.PARSER)
        val location: Variable<Location> = instruction.get(Argument.LOCATION)
        return NxBlock(itemID, location)
    }
}

class NxBlock(
    private val itemID: Variable<String>,
    private val location: Variable<Location>
) : NullableCondition {

    override fun check(profile: Profile?): Boolean {
        val loc = location.getValue(profile)
        val id = itemID.getValue(profile)

        val mechanic = NexoBlocks.customBlockMechanic(loc) ?: return false
        return mechanic.factory?.mechanicID == id
    }
}