package com.github.mrjimin.betonquestadder

import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.argument.Argument
import com.nexomc.nexo.api.NexoItems

class ItemIdParser : Argument<String> {

    override fun apply(value: String?): String = value?.trim()?.takeIf { NexoItems.exists(it) }
        ?: throw QuestException("Invalid or missing Nexo Item ID: '$value'")

    companion object {
        val PARSER = ItemIdParser()
    }
}

