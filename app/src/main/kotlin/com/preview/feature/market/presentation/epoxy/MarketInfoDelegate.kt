package com.preview.feature.market.presentation.epoxy

import com.preview.base.LcenState
import com.preview.feature.market.presentation.marketStateView
import com.preview.feature.market.presentation.model.MarketInfoItemUiState
import com.preview.feature.market.presentation.model.MarketInfoUiState

internal interface MarketInfoDelegate {
    fun MarketEpoxyController.buildMarketModels(marketState: MarketInfoUiState)
}

internal class MarketInfoDelegateImpl : MarketInfoDelegate {
    override fun MarketEpoxyController.buildMarketModels(marketState: MarketInfoUiState) {
        when(marketState.lcenState) {
            is LcenState.Content -> buildContent(marketState.marketItem.asContent())
            is LcenState.Error, LcenState.None -> Unit
            LcenState.Loading -> buildLoading()
        }
    }

    private fun MarketEpoxyController.buildContent(state: MarketInfoItemUiState) {
        marketStateView {
            id("market_state")
            bind(state)
        }
    }

    private fun MarketEpoxyController.buildLoading() {
        marketStateView {
            id("market_state")
        }
    }
}