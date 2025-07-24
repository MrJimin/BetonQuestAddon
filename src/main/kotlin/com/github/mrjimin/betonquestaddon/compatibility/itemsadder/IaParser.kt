package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.util.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.argument.Argument

class IaParser : Argument<CustomStack> {

    init {
        if (!"ItemsAdder".checkPlugin()) throw NotFoundPlugin("ItemsAdder")
    }

    companion object {
        val PARSER = IaParser()
    }

    override fun apply(value: String?): CustomStack = value?.trim()?.let { CustomStack.getInstance(it) }
            ?: throw QuestException("Invalid or missing ItemsAdder Item ID: '$value'")
}