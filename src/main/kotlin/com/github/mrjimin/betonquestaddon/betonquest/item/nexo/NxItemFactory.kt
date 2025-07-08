package com.github.mrjimin.betonquestaddon.betonquest.item.nexo

import com.github.mrjimin.betonquestaddon.betonquest.parser.NxParser
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory

class NxItemFactory : TypeFactory<QuestItemWrapper> {

    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return NxItemWrapper(instruction.get(NxParser.PARSER))
    }
}