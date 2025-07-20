package com.github.mrjimin.betonquestaddon.betonquest.events

import com.github.mrjimin.betonquestaddon.betonquest.parser.IaParser
import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent
import org.bukkit.Location

class IaSetBlockAtEventFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemID: Variable<CustomStack> = instruction.get(IaParser.Companion.PARSER)
        val location = instruction.get(Argument.LOCATION)
        return PrimaryServerThreadEvent(IaSetBlockAt(itemID, location), data)
    }
}

class IaSetBlockAt(
    private val itemID: Variable<CustomStack>,
    private val location: Variable<Location>
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val loc = location.getValue(profile)
        val customStack = itemID.getValue(profile)

        if (!customStack.isBlock) {
            throw QuestException("ItemsAdder Item is not a block: $itemID")
        }
        CustomBlock.place(customStack.namespacedID, loc)
    }
}