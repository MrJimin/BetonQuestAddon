package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.events

import dev.lone.itemsadder.api.ItemsAdder
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.event.online.OnlineEvent
import org.betonquest.betonquest.instruction.variable.Variable

class IaPlayAnimation(
    private val name: Variable<String>
) : OnlineEvent {

    override fun execute(profile: OnlineProfile) {
        ItemsAdder.playTotemAnimation(profile.player, name.getValue(profile))
    }
}