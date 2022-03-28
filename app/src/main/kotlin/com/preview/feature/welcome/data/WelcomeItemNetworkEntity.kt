package com.preview.feature.welcome.data

import com.preview.base.Dto
import com.preview.feature.welcome.domain.model.WelcomeItemModel

data class WelcomeItemNetworkEntity(
    val title: String,
    val description: String,
): Dto<WelcomeItemModel> {

    override fun convert(): WelcomeItemModel {
        return WelcomeItemModel(
            title = title,
            description = description,
        )
    }
}