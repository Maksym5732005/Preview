package com.preview.feature.market.presentation.model

import com.preview.base.LcenState

data class MarketUiState(
    val marketStateItem: MarketInfoUiState,
    val preciousState: PreciousUiState,
    val baseMetalsItems: List<String>,
    val indicesItems: List<String>,
)

data class MarketInfoUiState(
    val lcenState: LcenState<Unit>,
    val marketItem: LcenState<MarketInfoItemUiState>,
)

data class PreciousUiState(
    val lcenState: LcenState<Unit>,
    val preciousItems: LcenState<List<MetalUiState>>,
)