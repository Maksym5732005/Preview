package com.preview.feature.welcome.data.entity

import com.preview.base.data.Dto
import com.preview.feature.welcome.domain.model.WelcomeItemModel
import com.preview.feature.welcome.domain.model.WelcomeTitles

data class WelcomeItemNetworkEntity(
    val title: String,
    val description: String,
): Dto<WelcomeItemModel> {

    override fun convert(): WelcomeItemModel {
        return WelcomeItemModel(
            title = title.toTitle(),
            description = description,
        )
    }

    private fun String.toTitle(): WelcomeTitles {
        return WelcomeTitles.valueOf(this)
    }
}