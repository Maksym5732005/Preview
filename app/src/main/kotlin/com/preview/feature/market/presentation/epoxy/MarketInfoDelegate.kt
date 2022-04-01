package com.preview.feature.market.presentation.epoxy

import com.preview.base.LcenState
import com.preview.base.extensions.isLoading
import com.preview.feature.market.presentation.view.marketStateLoadingView
import com.preview.feature.market.presentation.view.marketStateView
import com.preview.feature.market.presentation.model.MarketInfoItemUiState
import com.preview.feature.market.presentation.model.MarketInfoUiState

internal interface MarketInfoDelegate {
    fun MarketEpoxyController.buildMarketModels(marketState: MarketInfoUiState)
}

internal class MarketInfoDelegateImpl : MarketInfoDelegate {
    override fun MarketEpoxyController.buildMarketModels(marketState: MarketInfoUiState) {
        if (marketState.lcenState.isLoading()) {
            buildLoading()
        } else {
            when (marketState.marketItem) {
                is LcenState.Content -> buildContent(marketState.marketItem.asContent())
                is LcenState.Error, LcenState.None -> Unit
                LcenState.Loading -> buildLoading()
            }
        }
    }

    private fun MarketEpoxyController.buildContent(state: MarketInfoItemUiState) {
        marketStateView {
            id("market_state")
            bind(state)
        }
    }

    private fun MarketEpoxyController.buildLoading() {
        marketStateLoadingView {
            id("market_state_loading")
        }
    }
}