package com.preview.feature.welcome.presentation.model

import com.preview.base.LcenState

internal data class WelcomeUiState(
    val items: LcenState<List<WelcomeItemUiState>>,
)