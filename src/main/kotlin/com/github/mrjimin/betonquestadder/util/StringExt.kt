package com.github.mrjimin.betonquestadder.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

fun Component.toLegacy(): String = LegacyComponentSerializer.legacySection().serialize(this)