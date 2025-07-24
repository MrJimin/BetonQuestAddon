package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import dev.lone.itemsadder.api.CustomStack
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority

class IaBlockBreak(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<CustomStack>
) : IaBlockObjective(instruction, targetAmount, "blocks_to_break", log, itemID) {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun CustomBlockBreakEvent.onBlockBreak() {
        handle(namespacedID, player)
    }
}