package com.github.mrjimin.betonquestaddon.betonquest.item.nexo

import com.github.mrjimin.betonquestaddon.util.toLegacy
import com.nexomc.nexo.api.NexoItems
import com.nexomc.nexo.items.ItemBuilder
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.bukkit.inventory.ItemStack

class NxItem(
    private val itemId: String
) : QuestItem {

    private val itemBuilder: ItemBuilder? = NexoItems.itemFromId(itemId)

    override fun getName(): String? {
        return itemBuilder?.itemName?.toLegacy()
    }

    override fun getLore(): List<String?>? {
        return itemBuilder?.lore?.map { it.toLegacy() }
    }

    override fun generate(int: Int, profile: Profile?): ItemStack? {
        val item = NexoItems.itemFromId(itemId) ?: return null
        item.setAmount(int)
        return item.build()
    }

    override fun matches(item: ItemStack?): Boolean {
        val matchedId = NexoItems.idFromItem(item)
        return matchedId == itemId
    }
}