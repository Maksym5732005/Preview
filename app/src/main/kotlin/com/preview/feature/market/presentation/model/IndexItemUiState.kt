package com.preview.feature.market.presentation.model

import androidx.annotation.ColorRes

data class IndexItemUiState(
    val time: String,
    val indexName: String,
    val price: String,
    val change: String,
    @ColorRes val changeColorRes: Int,
    val changePercent: String,
    @ColorRes val changePercentColorRes: Int,
)
