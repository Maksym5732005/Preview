package com.preview.feature.market.presentation.epoxy

import com.preview.R
import com.preview.base.domain.LcenState
import com.preview.base.extensions.isLoading
import com.preview.base.presentation.views.dividerView
import com.preview.feature.market.presentation.model.IndexItemUiState
import com.preview.feature.market.presentation.model.IndicesUiState
import com.preview.feature.market.presentation.view.TitleModel
import com.preview.feature.market.presentation.view.marketItemDataLoadingView
import com.preview.feature.market.presentation.view.marketItemDataTitleView
import com.preview.feature.market.presentation.view.marketItemIndexView

private const val LOADING_VIEWS_COUNT = 6

internal interface IndicesDelegate {
    fun MarketEpoxyController.buildIndicesModels(state: IndicesUiState)
}

internal class IndicesDelegateImpl : IndicesDelegate {
    override fun MarketEpoxyController.buildIndicesModels(state: IndicesUiState) {
        marketItemDataTitleView {
            id("index_title")
            title(
                TitleModel(
                    title = R.string.markets_item_indices_title,
                    subtitleSecond = R.string.markets_item_header_price,
                    subtitleThird = R.string.markets_item_header_change,
                    subtitleFifth = R.string.markets_item_header_change_percent,
                )
            )
        }
        if (state.lcenState.isLoading()) {
            buildLoading()
        } else {
            when (state.indicesItems) {
                is LcenState.Content -> buildContent(state.indicesItems.asContent())
                is LcenState.Error, LcenState.None -> Unit
                LcenState.Loading -> buildLoading()
            }
        }
    }

    private fun MarketEpoxyController.buildContent(items: List<IndexItemUiState>) {
        items.forEachIndexed { i, uiState ->
            marketItemIndexView {
                id("index_$i")
                bind(uiState)
                clickListener { this@buildContent.callbacks.indexClicked(uiState.indexName) }
            }
            dividerView { id("index_divider_$i") }
        }
    }

    private fun MarketEpoxyController.buildLoading() {
        repeat(LOADING_VIEWS_COUNT) {
            marketItemDataLoadingView {
                id("index_loading_$it")
            }
            dividerView { id("index_divider_$it") }
        }
    }
}