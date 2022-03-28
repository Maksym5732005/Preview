package com.preview.feature.welcome.domain.model

data class WelcomeItemModel(
    val title: WelcomeTitles,
    val description: String,
)

enum class WelcomeTitles { Market, Permission }
