package com.github.mrjimin.betonquestaddon.util

import net.kyori.adventure.text.Component
import org.betonquest.betonquest.conversation.Conversation
import org.bukkit.entity.Player

//fun Conversation.sendTitle(
//    title: Component,
//    subtitle: Component = Component.empty(),
//    fadeIn: Int = 10,
//    stay: Int = 70,
//    fadeOut: Int = 20
//) {
//    val player: Player = this.
//    if (this.interceptor == null) {
//        // interceptor가 없으면 직접 Player에 보냄
//        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut)
//    } else {
//        // interceptor가 있으면 interceptor에 위임
//        this.interceptor.sendTitle(title, subtitle, fadeIn, stay, fadeOut)
//    }
//}