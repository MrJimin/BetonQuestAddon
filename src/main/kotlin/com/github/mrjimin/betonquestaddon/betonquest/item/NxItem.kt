package com.github.mrjimin.betonquestaddon.betonquest.item

import com.github.mrjimin.betonquestaddon.betonquest.parser.NxParser
import com.github.mrjimin.betonquestaddon.util.toLegacy
import com.nexomc.nexo.api.NexoItems
import com.nexomc.nexo.items.ItemBuilder
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemSerializer
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory
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

class NxItemFactory : TypeFactory<QuestItemWrapper> {

    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return NxItemWrapper(instruction.get(NxParser.Companion.PARSER))
    }
}

class NxItemSerializer : QuestItemSerializer {

    override fun serialize(itemStack: ItemStack): String {
        if (!itemStack.hasItemMeta()) {
            throw QuestException("ItemStack is null or missing metadata.")
        }

        val id = NexoItems.idFromItem(itemStack)
        if (id == null) {
            throw QuestException("The item does not contain a valid Nexo ID.")
        }

        return id
    }
}

class NxItemWrapper(
    private val itemId: Variable<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem {
        return NxItem(itemId.getValue(profile))
    }
}