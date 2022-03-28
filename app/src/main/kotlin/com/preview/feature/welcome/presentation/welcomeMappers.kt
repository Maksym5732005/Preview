package com.preview.feature.welcome.presentation

import com.preview.base.LcenState
import com.preview.base.extensions.mapContent
import com.preview.feature.welcome.domain.model.WelcomeItemModel
import com.preview.feature.welcome.presentation.model.WelcomeItemUiEntity

internal fun LcenState<List<WelcomeItemModel>>.mapToUiEntity(): LcenState<List<WelcomeItemUiEntity>> {
    return mapContent { content ->
        content.map { WelcomeItemUiEntity(it.title, it.description) }
    }
}