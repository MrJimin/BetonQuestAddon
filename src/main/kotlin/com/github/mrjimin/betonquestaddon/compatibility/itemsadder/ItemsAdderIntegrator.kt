package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.conditions.IaBlockConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.events.IaPlayAnimationEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.events.IaSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.items.IaItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.items.IaItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.IaBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.IaBlockPlaceObjectiveFactory

object ItemsAdderIntegrator : BQAddonIntegrator() {

    override fun hook() {

        condition.registerCombined("iaBlockAt", IaBlockConditionFactory(data))
        event.apply {
            register("iaBlockAt", IaSetBlockAtEventFactory(data))
            register("iaPlayAnimation", IaPlayAnimationEventFactory(loggerFactory, data))
        }
        objective.apply {
            register("iaBlockBreak", IaBlockBreakObjectiveFactory(loggerFactory))
            register("iaBlockPlace", IaBlockPlaceObjectiveFactory(loggerFactory))
        }
        registerItem("ia", IaItemFactory(), IaItemSerializer())
    }

}