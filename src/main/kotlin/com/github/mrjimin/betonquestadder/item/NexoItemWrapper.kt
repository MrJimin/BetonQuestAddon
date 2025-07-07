package com.github.mrjimin.betonquestadder.item

import com.nexomc.nexo.api.NexoItems
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.Bukkit

class NexoItemWrapper(
    private val itemId: Variable<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem? {
        val id = itemId.getValue(profile)
        if (id == null) {
            Bukkit.getLogger().warning("[NexoItemWrapper] Failed to resolve itemId from variable.")
            return null
        }

        val exists = NexoItems.exists(id)
        if (!exists) {
            Bukkit.getLogger().warning("[NexoItemWrapper] Item ID '$id' not registered in NexoItems.")
        }

        return NexoItem(id)
    }
}
