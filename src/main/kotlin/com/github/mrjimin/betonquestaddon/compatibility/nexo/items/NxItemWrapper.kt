package com.github.mrjimin.betonquestaddon.compatibility.nexo.items

import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper

class NxItemWrapper(
    private val itemId: Variable<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem {
        return NxItem(itemId.getValue(profile))
    }
}