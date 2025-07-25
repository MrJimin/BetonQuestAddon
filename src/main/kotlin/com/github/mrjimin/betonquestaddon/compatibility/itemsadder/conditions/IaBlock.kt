package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.conditions

import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.Location

class IaBlock(
    private val itemID: Variable<CustomStack>,
    private val location: Variable<Location?>
) : NullableCondition {

    override fun check(profile: Profile?): Boolean {
        val block = CustomBlock.byAlreadyPlaced(location.getValue(profile)!!.block)
        return block != null && block.customStack.matchNamespacedID(itemID.getValue(profile))
    }
}