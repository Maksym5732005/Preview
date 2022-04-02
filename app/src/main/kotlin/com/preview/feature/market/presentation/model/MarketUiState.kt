package com.preview.feature.market.presentation.model

import com.preview.base.LcenState

data class MarketUiState(
    val marketStateItem: MarketInfoUiState,
    val preciousState: PreciousUiState,
    val baseMetalsState: BaseUiState,
    val indicesState: IndicesUiState,
)

data class MarketInfoUiState(
    val lcenState: LcenState<Unit>,
    val marketItem: LcenState<MarketInfoItemUiState>,
)

data class PreciousUiState(
    val lcenState: LcenState<Unit>,
    val preciousItems: LcenState<List<MetalUiState>>,
)

data class BaseUiState(
    val lcenState: LcenState<Unit>,
    val preciousItems: LcenState<List<MetalUiState>>,
)

data class IndicesUiState(
    val lcenState: LcenState<Unit>,
    val indicesItems: LcenState<List<IndexItemUiState>>,
)