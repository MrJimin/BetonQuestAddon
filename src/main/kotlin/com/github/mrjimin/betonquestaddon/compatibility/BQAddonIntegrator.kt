package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestComp
import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestComp.featureRegistries
import org.betonquest.betonquest.item.QuestItemSerializer
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory
import org.betonquest.betonquest.kernel.registry.quest.ConditionTypeRegistry
import org.betonquest.betonquest.kernel.registry.quest.EventTypeRegistry
import org.betonquest.betonquest.kernel.registry.quest.ObjectiveTypeRegistry
import org.betonquest.betonquest.kernel.registry.quest.VariableTypeRegistry

abstract class BQAddonIntegrator {

    protected val loggerFactory = BetonQuestComp.loggerFactory
    protected val data = BetonQuestComp.data
    protected val pluginMessage = BetonQuestComp.instance.pluginMessage
    protected val variableProcessor = BetonQuestComp.instance.variableProcessor
    protected val registries = BetonQuestComp.questRegistries

    protected val condition: ConditionTypeRegistry = registries.condition
    protected val event: EventTypeRegistry = registries.event
    protected val objective: ObjectiveTypeRegistry = registries.objective
    protected val variable: VariableTypeRegistry = registries.variable

    protected fun registerItem(
        name: String,
        factory: TypeFactory<QuestItemWrapper>,
        serializer: QuestItemSerializer
    ) {
        val itemRegistry = featureRegistries.item()
        itemRegistry.register(name, factory)
        itemRegistry.registerSerializer(name, serializer)
    }

    abstract fun hook()
}
