package com.github.mrjimin.betonquestaddon.betonquest.events.itemsadder

import com.github.mrjimin.betonquestaddon.betonquest.parser.IaParser
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent

class IaSetBlockAtEventFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemID: Variable<CustomStack> = instruction.get(IaParser.PARSER)
        val location = instruction.get(Argument.LOCATION)
        return PrimaryServerThreadEvent(IaSetBlockAt(itemID, location), data)
    }
}