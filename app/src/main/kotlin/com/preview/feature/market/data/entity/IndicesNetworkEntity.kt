package com.preview.feature.market.data.entity

import com.preview.base.data.Dto
import com.preview.feature.market.domain.model.MarketItemIndex
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat

private const val TIME_STAMP_FORMAT = "yyyy-MM-dd HH:mm:ss"

data class IndicesNetworkEntity(
    val changePercentage: Float,
    val price: Float,
    val symbol: String,
    val change: Float,
    val timestamp: String,
) : Dto<MarketItemIndex> {

    override fun convert(): MarketItemIndex {
        return MarketItemIndex(
            time = LocalDateTime.parse(timestamp, DateTimeFormat.forPattern(TIME_STAMP_FORMAT)),
            indexName = symbol,
            price = price,
            change = change,
            changePercent = changePercentage,
        )
    }
}