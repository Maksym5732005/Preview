package com.preview.feature.market.domain.model

import org.joda.time.LocalDateTime

data class MarketItemIndex(
    val time: LocalDateTime,
    val indexName: String,
    val price: Float,
    val change: Float,
    val changePercent: Float,
)
