package com.preview.feature.welcome.presentation.model

import com.preview.base.LcenState

internal data class WelcomeViewState(
    val items: LcenState<List<WelcomeItemUiEntity>>,
)