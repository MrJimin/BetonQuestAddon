package com.github.mrjimin.betonquestadder.item

import com.github.mrjimin.betonquestadder.ItemIdParser
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory

class ItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction?): QuestItemWrapper {
        val id: Variable<String> = instruction?.get(ItemIdParser.PARSER)
            ?: throw QuestException("Missing item ID in instruction.")
        return NexoItemWrapper(id)
    }
}
