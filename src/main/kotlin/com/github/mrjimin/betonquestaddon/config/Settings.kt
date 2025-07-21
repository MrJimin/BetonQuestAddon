package com.github.mrjimin.betonquestaddon.config

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin

enum class Settings(
    val path: String,
    val default: Any? = null
) {
    // setting
    AUTO_RELOAD("setting.auto-reload", false),

    // objectives
    OBJ_CHAT("objectives.chatObjective.argumentName","cancel");

    private val config
        get() = BetonQuestAddonPlugin.instance.config

    fun get(): Any? = config.get(path) ?: default

    override fun toString(): String = get().toString()
    fun toBoolean(): Boolean = get().toString().toBoolean()
}