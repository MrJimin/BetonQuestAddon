package com.github.mrjimin.betonquestadder.item

import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper

class NexoItemWrapper(
    private val itemId: Variable<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem? {
        val id = itemId.getValue(profile)
        return NexoItem(id)
    }
}
