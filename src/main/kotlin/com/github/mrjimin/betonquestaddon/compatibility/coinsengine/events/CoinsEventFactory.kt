package com.github.mrjimin.betonquestaddon.compatibility.coinsengine.events

import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.config.PluginMessage
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.kernel.processor.quest.VariableProcessor
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.IngameNotificationSender
import org.betonquest.betonquest.quest.event.NotificationLevel
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent
import com.github.mrjimin.betonquestaddon.compatibility.coinsengine.events.CoinsEvent.Operation

class CoinsEventFactory(
    private val loggerFactory: BetonQuestLoggerFactory,
    private val data: PrimaryServerThreadData,
    private val pluginMessage: PluginMessage,
    private val variableProcessor: VariableProcessor
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val currencyId = instruction.next()
        val rawAmount = instruction.next()

        val (operation, numericPart) = when {
            rawAmount.startsWith("+") -> Operation.ADD to rawAmount.substring(1)
            rawAmount.startsWith("-") -> Operation.REMOVE to rawAmount.substring(1)
            rawAmount.startsWith("*") -> Operation.MULTIPLY to rawAmount.substring(1)
            else -> Operation.SET to rawAmount
        }

        val amount = try {
            Variable(variableProcessor, instruction.getPackage(), numericPart, Argument.NUMBER)
        } catch (ex: QuestException) {
            throw QuestException("Could not parse coin amount: '${rawAmount}' → ${ex.message}", ex)
        }

        val notify = instruction.hasArgument("notify")
        val (givenSender, takenSender, resetSender) = if (notify) {
            val log = loggerFactory.create(CoinsEvent::class.java)
            val pack = instruction.getPackage()
            val fullID = instruction.id.fullID
            Triple(
                IngameNotificationSender(log, pluginMessage, pack, fullID, NotificationLevel.INFO, "coins_given"),
                IngameNotificationSender(log, pluginMessage, pack, fullID, NotificationLevel.INFO, "coins_taken"),
                IngameNotificationSender(log, pluginMessage, pack, fullID, NotificationLevel.INFO, "coins_reset")
            )
        } else Triple(null, null, null)

        val event = CoinsEvent(currencyId, amount, operation, givenSender, takenSender, resetSender)
        return PrimaryServerThreadEvent(event, data)
    }
}
