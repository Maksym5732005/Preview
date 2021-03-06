package com.preview.feature.market.presentation.epoxy

import com.preview.R
import com.preview.base.domain.LcenState
import com.preview.base.extensions.isLoading
import com.preview.base.presentation.views.dividerView
import com.preview.feature.market.presentation.model.BaseUiState
import com.preview.feature.market.presentation.model.MetalUiState
import com.preview.feature.market.presentation.view.TitleModel
import com.preview.feature.market.presentation.view.marketItemDataLoadingView
import com.preview.feature.market.presentation.view.marketItemDataTitleView
import com.preview.feature.market.presentation.view.marketItemDataView

private const val LOADING_VIEWS_COUNT = 6

internal interface BaseMetalsDelegate {
    fun MarketEpoxyController.buildBaseMetalModels(marketState: BaseUiState)
}

internal class BaseMetalsDelegateImpl : BaseMetalsDelegate {
    override fun MarketEpoxyController.buildBaseMetalModels(marketState: BaseUiState) {
        marketItemDataTitleView {
            id("base_title")
            title(
                TitleModel(
                    title = R.string.markets_item_base_title,
                    subtitleSecond = R.string.markets_item_header_bid,
                    subtitleThird = R.string.markets_item_header_ask,
                    subtitleFifth = R.string.markets_item_header_change_percent,
                )
            )
        }
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

    private fun MarketEpoxyController.buildContent(items: List<MetalUiState>) {
        items.forEachIndexed { i, uiState ->
            marketItemDataView {
                id("base_$i")
                bind(uiState)
                clickListener { this@buildContent.callbacks.metalClicked(it) }
            }
            dividerView { id("base_divider_$i") }
        }
    }

    private fun MarketEpoxyController.buildLoading() {
        repeat(LOADING_VIEWS_COUNT) {
            marketItemDataLoadingView {
                id("base_loading_$it")
            }
            dividerView { id("base_divider_$it") }
        }
    }
}