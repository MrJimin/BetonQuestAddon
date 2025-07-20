package com.github.mrjimin.betonquestaddon.betonquest.item

import com.github.mrjimin.betonquestaddon.betonquest.parser.IaParser
import com.github.mrjimin.betonquestaddon.util.toLegacy
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemSerializer
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory
import org.bukkit.inventory.ItemStack

class IaItem(
    private val stack: CustomStack
) : QuestItem {

    override fun generate(stackSize: Int, profile: Profile?): ItemStack {
        val itemStack = stack.itemStack.clone()
        itemStack.amount = stackSize
        return itemStack
    }

    override fun matches(item: ItemStack?): Boolean {
        val customStack = CustomStack.byItemStack(item)
        if (customStack == null) {
            return false
        }
        return customStack.namespacedID == stack.namespacedID
    }

    override fun getName(): String? {
        return stack.displayName
    }

    override fun getLore(): List<String?>? {
        val itemStack = stack.itemStack
        return itemStack.lore()?.map { it.toLegacy() }
    }
}

class IaItemFactory : TypeFactory<QuestItemWrapper> {

    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return IaItemWrapper(instruction.get(IaParser.Companion.PARSER))
    }
}

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

class IaItemWrapper(
    private val stack: Variable<CustomStack>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem {
        return IaItem(stack.getValue(profile))
    }
}