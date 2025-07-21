package com.github.mrjimin.betonquestaddon.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

fun Component.toLegacy(): String = LegacyComponentSerializer.legacySection().serialize(this)
fun Component.toPlainText(): String = PlainTextComponentSerializer.plainText().serialize(this)
fun String.toMiniMessage(): Component = MiniMessage.miniMessage().deserialize(this)