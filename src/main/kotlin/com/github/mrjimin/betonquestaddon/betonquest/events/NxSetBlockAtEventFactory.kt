package com.github.mrjimin.betonquestaddon.betonquest.events

import com.github.mrjimin.betonquestaddon.betonquest.events.NxSetBlockAt
import com.github.mrjimin.betonquestaddon.betonquest.parser.NxParser
import com.nexomc.nexo.api.NexoBlocks
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

class NxSetBlockAtEventFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemID: Variable<String> = instruction.get(NxParser.Companion.PARSER)
        val location: Variable<Location> = instruction.get(Argument.LOCATION)
        return PrimaryServerThreadEvent(NxSetBlockAt(itemID, location), data)
    }
}

class NxSetBlockAt(
    private val itemID: Variable<String>,
    private val loc: Variable<Location>
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val loc = loc.getValue(profile)
        val id = itemID.getValue(profile)

        if (!NexoBlocks.isCustomBlock(id)) {
            throw QuestException("Nexo item is not a block: $id")
        }

        NexoBlocks.place(id, loc)
    }
}