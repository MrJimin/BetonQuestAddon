package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class NxBlockBreak(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<String>
) : NxBlockObjective(instruction, targetAmount, "blocks_to_break", log, itemID), Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun NexoBlockBreakEvent.onNexoBlockBreak() {
        handle(mechanic.itemID, player)
    }
}