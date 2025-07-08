package com.github.mrjimin.betonquestaddon.betonquest.item.itemsadder

import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper

class IaItemWrapper(
    private val stack: Variable<CustomStack>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem {
        return IaItem(stack.getValue(profile))
    }
}