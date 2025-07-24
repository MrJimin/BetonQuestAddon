package com.github.mrjimin.betonquestaddon.compatibility.nexo.items

import com.github.mrjimin.betonquestaddon.compatibility.nexo.NxParser
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory

class NxItemFactory : TypeFactory<QuestItemWrapper> {

    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return NxItemWrapper(instruction.get(NxParser.Companion.PARSER))
    }
}