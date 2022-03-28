package com.preview.base

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

interface Event

data class DebugMessageEvent(val msg: String) : Event

sealed class NavigationEvent : Event {
    data class NavigationId(@IdRes val destinationId: Int) : NavigationEvent()
    data class NavigationDirection(val direction: NavDirections) : NavigationEvent()
}