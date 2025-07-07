//package com.github.mrjimin.betonquestadder.conditions
//
//import com.nexomc.nexo.api.NexoBlocks
//import com.nexomc.nexo.api.NexoItems
//import org.betonquest.betonquest.api.profile.Profile
//import org.betonquest.betonquest.api.quest.QuestException
//import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
//import org.betonquest.betonquest.instruction.variable.Variable
//import org.bukkit.Location
//
//class NeBlock(
//    private val itemId: Variable<NexoItems>,
//    private val location: Variable<Location>
//) : NullableCondition {
//
//    @Throws(QuestException::class)
//    override fun check(profile: Profile?): Boolean {
//        val loc = location.getValue(profile)
//        val block = NexoBlocks.by
//        return block != null && block.customStack.matchNamespacedID(itemID.getValue(profile))
//    }
//}