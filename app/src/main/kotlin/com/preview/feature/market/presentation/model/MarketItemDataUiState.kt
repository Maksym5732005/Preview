package com.preview.feature.market.presentation.model

import androidx.annotation.ColorRes

data class MetalUiState(
    val time: String,
    val metalName: String,
    val bid: String,
    val ask: String,
    val change: String,
    @ColorRes val changeColorRes: Int,
)
