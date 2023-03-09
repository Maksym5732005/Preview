package com.preview.feature.market.presentation

import com.preview.R
import com.preview.base.domain.LcenState
import com.preview.base.extensions.mapContent
import com.preview.base.presentation.ResourceReader
import com.preview.feature.market.domain.model.MarketInfo
import com.preview.feature.market.domain.model.MarketItemIndex
import com.preview.feature.market.domain.model.MarketItemMetal
import com.preview.feature.market.presentation.model.IndexItemUiState
import com.preview.feature.market.presentation.model.MarketInfoItemUiState
import com.preview.feature.market.presentation.model.MetalUiState
import org.joda.time.format.DateTimeFormat

private const val DATE_TIME_PATTERN = "E d MMM-yyyy HH:mm:ss"
private const val ITEM_DATE_TIME_PATTERN = "HH:mm"
private const val ITEM_VALUE_NUMBER_FORMAT = "%.2f"

fun LcenState<MarketInfo>.convertToUiState(resourceReader: ResourceReader): LcenState<MarketInfoItemUiState> {
    return mapContent { info ->
        MarketInfoItemUiState(
            dateTime = DateTimeFormat.forPattern(DATE_TIME_PATTERN).print(info.time),
            openText = if (info.isMarketOpen) {
                resourceReader.getString(R.string.markets_item_market_state_open)
            } else {
                resourceReader.getString(R.string.markets_item_market_state_close)
            },
            workingTime = info.closeAt,
        )
    }
}

fun MarketItemMetal.convertToUiState(): MetalUiState {
    return MetalUiState(
        time = DateTimeFormat.forPattern(ITEM_DATE_TIME_PATTERN).print(time),
        metalName = metalName,
        bid = String.format(ITEM_VALUE_NUMBER_FORMAT, bid),
        ask = String.format(ITEM_VALUE_NUMBER_FORMAT, ask),
        change = change.percentFormat(),
        changeColorRes = change.resolveColor(),
    )
}

fun MarketItemIndex.convertToUiState(): IndexItemUiState {
    return IndexItemUiState(
        time = DateTimeFormat.forPattern(ITEM_DATE_TIME_PATTERN).print(time),
        indexName = indexName,
        price = String.format(ITEM_VALUE_NUMBER_FORMAT, price),
        change = String.format(ITEM_VALUE_NUMBER_FORMAT, change),
        changeColorRes = change.resolveColor(),
        changePercent = changePercent.percentFormat(),
        changePercentColorRes = changePercent.resolveColor(),
    )
}

private fun Float.percentFormat(): String {
    return buildString {
        if (this@percentFormat > 0.00) append("+")
        append(String.format(ITEM_VALUE_NUMBER_FORMAT, this@percentFormat))
        append("%")
    }
}

private fun Float.resolveColor(): Int {
    return when {
        this > 0 -> R.color.green
        this < 0 -> R.color.red
        else -> R.color.gray
    }
}