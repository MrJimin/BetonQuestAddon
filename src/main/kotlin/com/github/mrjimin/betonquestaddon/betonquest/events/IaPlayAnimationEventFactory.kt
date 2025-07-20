package com.github.mrjimin.betonquestaddon.betonquest.events

import dev.lone.itemsadder.api.ItemsAdder
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.quest.event.online.OnlineEvent
import org.betonquest.betonquest.api.quest.event.online.OnlineEventAdapter
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent

class IaPlayAnimationEventFactory(
    private val loggerFactory: BetonQuestLoggerFactory,
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val animation = instruction.get(Argument.STRING)
        return PrimaryServerThreadEvent(
            OnlineEventAdapter(
                IaPlayAnimation(animation),
                loggerFactory.create(IaPlayAnimation::class.java),
                instruction.getPackage()
            ), data
        )
    }
}

class IaPlayAnimation(
    private val name: Variable<String>
) : OnlineEvent {

    override fun execute(profile: OnlineProfile) {
        ItemsAdder.playTotemAnimation(profile.player, name.getValue(profile))
    }
}