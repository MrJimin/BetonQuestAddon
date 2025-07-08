package com.github.mrjimin.betonquestaddon.betonquest.conditions.nexo

import com.nexomc.nexo.api.NexoBlocks
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.Location

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