package com.preview.feature.market.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.preview.feature.market.presentation.model.MarketUiState

internal class MarketEpoxyController(
    val callbacks: MarketEpoxyControllerCallbacks,
) : TypedEpoxyController<MarketUiState>(),
    MarketInfoDelegate by MarketInfoDelegateImpl(),
    PreciousDelegate by PreciousDelegateImpl(),
    BaseMetalsDelegate by BaseMetalsDelegateImpl(),
    IndicesDelegate by IndicesDelegateImpl() {

    override fun buildModels(data: MarketUiState) {
        buildMarketModels(data.marketStateItem)
        buildPreciousMetalModels(data.preciousState)
        buildBaseMetalModels(data.baseMetalsState)
        buildIndicesModels(data.indicesState)
    }
}

interface MarketEpoxyControllerCallbacks {
    fun metalClicked(metal: String)
    fun indexClicked(index: String)
}