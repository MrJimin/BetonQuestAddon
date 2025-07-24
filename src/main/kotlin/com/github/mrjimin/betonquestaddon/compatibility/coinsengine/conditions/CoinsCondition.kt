package com.github.mrjimin.betonquestaddon.compatibility.coinsengine.conditions

import com.github.mrjimin.betonquestaddon.hook.CoinsEngineHook
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.instruction.variable.Variable

class CoinsCondition(
    private val currencyId: String,
    private val amount: Variable<Number>
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val userBalance = CoinsEngineHook.getBalance(profile.player.uniqueId, currencyId)
        val requiredAmount = amount.getValue(profile).toDouble()
        return userBalance >= requiredAmount
    }
}