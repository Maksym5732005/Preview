package com.preview.feature.market.presentation.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.preview.feature.market.presentation.model.MarketUiState

internal class MarketEpoxyController(
    val callbacks: MarketEpoxyControllerCallbacks,
) : TypedEpoxyController<MarketUiState>(),
    MarketInfoDelegate by MarketInfoDelegateImpl() {

    override fun buildModels(data: MarketUiState) {
        buildMarketModels(data.marketStateItem)
    }
}

interface MarketEpoxyControllerCallbacks {

}