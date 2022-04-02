package com.preview.feature.market.presentation.epoxy

import com.preview.base.LcenState
import com.preview.base.extensions.isLoading
import com.preview.base.uicomponent.dividerView
import com.preview.feature.market.presentation.model.MarketItemDataUiState
import com.preview.feature.market.presentation.model.MarketItemDataUiState.DataUiState
import com.preview.feature.market.presentation.model.MarketItemDataUiState.TitleUiState
import com.preview.feature.market.presentation.model.PreciousUiState
import com.preview.feature.market.presentation.view.marketItemDataLoadingView
import com.preview.feature.market.presentation.view.marketItemDataView

private const val LOADING_VIEWS_COUNT = 5

internal interface PreciousDelegate {
    fun MarketEpoxyController.buildPreciousMetalModels(marketState: PreciousUiState)
}

internal class PreciousDelegateImpl : PreciousDelegate {
    override fun MarketEpoxyController.buildPreciousMetalModels(marketState: PreciousUiState) {
        if (marketState.lcenState.isLoading()) {
            buildLoading()
        } else {
            when (marketState.preciousItems) {
                is LcenState.Content -> buildContent(marketState.preciousItems.asContent())
                is LcenState.Error, LcenState.None -> Unit
                LcenState.Loading -> buildLoading()
            }
        }
    }

    private fun MarketEpoxyController.buildContent(items: List<MarketItemDataUiState>) {
        items.forEachIndexed { i, uiState ->
            when(uiState) {
                is TitleUiState -> Unit
                is DataUiState -> {
                    marketItemDataView {
                        id("data_$i")
                        bind(uiState)
                    }
                    dividerView { id("divider_$i") }
                }
            }
        }
    }

    private fun MarketEpoxyController.buildLoading() {
        repeat(LOADING_VIEWS_COUNT) {
            marketItemDataLoadingView {
                id("data_loading_$it")
            }
            dividerView { id("divider_$it") }
        }
    }
}