package com.preview.feature.market.presentation.model

import com.preview.base.LcenState

data class MarketUiState(
    val marketStateItem: MarketInfoUiState,
    val preciousMetalsItems: List<String>,
    val baseMetalsItems: List<String>,
    val indicesItems: List<String>,
)

data class MarketInfoUiState(
    val lcenState: LcenState<Unit>,
    val marketItem: LcenState<MarketInfoItemUiState>,
)