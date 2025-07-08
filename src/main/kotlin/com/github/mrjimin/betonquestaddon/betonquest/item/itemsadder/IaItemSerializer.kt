package com.github.mrjimin.betonquestaddon.betonquest.item.itemsadder

import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

class IaItemSerializer : QuestItemSerializer {

    override fun serialize(itemStack: ItemStack): String {
        if (!itemStack.hasItemMeta()) {
            throw QuestException("The item is not a ItemsAdder Item!")
        }
        val customStack = CustomStack.byItemStack(itemStack)
        if (customStack == null) {
            throw QuestException("The item is not a ItemsAdder Item!")
        }
        return customStack.namespacedID
    }
}