package com.preview.feature.welcome.presentation

import com.preview.base.domain.LcenState
import com.preview.base.extensions.mapContent
import com.preview.feature.welcome.domain.model.WelcomeItemModel
import com.preview.feature.welcome.presentation.model.WelcomeItemUiState

internal fun LcenState<List<WelcomeItemModel>>.mapToUiState(): LcenState<List<WelcomeItemUiState>> {
    return mapContent { content ->
        content.map { WelcomeItemUiState(it.title.name, it.description) }
    }
}