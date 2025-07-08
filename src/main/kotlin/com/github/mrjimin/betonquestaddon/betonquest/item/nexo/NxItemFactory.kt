package com.github.mrjimin.betonquestaddon.betonquest.item.nexo

import com.github.mrjimin.betonquestaddon.betonquest.item.nexo.NxItemWrapper
import com.github.mrjimin.betonquestaddon.betonquest.parser.NxParser
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory

class NxItemFactory : TypeFactory<QuestItemWrapper> {

    override fun parseInstruction(instruction: Instruction?): QuestItemWrapper {
        val id: Variable<String> = instruction?.get(NxParser.PARSER)
            ?: throw QuestException("Missing item ID in instruction.")
        return NxItemWrapper(id)
    }
}