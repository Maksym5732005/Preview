package com.preview.feature.market.presentation

import com.preview.base.domain.LcenState
import com.preview.feature.market.presentation.model.IndexItemUiState
import com.preview.feature.market.presentation.model.MarketInfoItemUiState
import com.preview.feature.market.presentation.model.MarketUiState
import com.preview.feature.market.presentation.model.MetalUiState

fun MarketUiState.updateMarketInfoLcenState(newState: LcenState<Unit>): MarketUiState {
    return copy(marketStateItem = marketStateItem.copy(lcenState = newState))
}

fun MarketUiState.updateMarketInfoContent(content: LcenState<MarketInfoItemUiState>): MarketUiState {
    return copy(marketStateItem = marketStateItem.copy(marketItem = content))
}

fun MarketUiState.updatePreciousMetalsLcenState(newState: LcenState<Unit>): MarketUiState {
    return copy(preciousState = preciousState.copy(lcenState = newState))
}

fun MarketUiState.updatePreciousMetalsContent(content: LcenState<List<MetalUiState>>): MarketUiState {
    return copy(preciousState = preciousState.copy(preciousItems = content))
}

fun MarketUiState.updateBaseMetalsLcenState(newState: LcenState<Unit>): MarketUiState {
    return copy(baseMetalsState = baseMetalsState.copy(lcenState = newState))
}

fun MarketUiState.updateBaseMetalsContent(content: LcenState<List<MetalUiState>>): MarketUiState {
    return copy(baseMetalsState = baseMetalsState.copy(preciousItems = content))

}

fun MarketUiState.updateIndicesLcenState(newState: LcenState<Unit>): MarketUiState {
    return copy(indicesState = indicesState.copy(lcenState = newState))
}

fun MarketUiState.updateIndicesContent(content: LcenState<List<IndexItemUiState>>): MarketUiState {
    return copy(indicesState = indicesState.copy(indicesItems = content))
}
