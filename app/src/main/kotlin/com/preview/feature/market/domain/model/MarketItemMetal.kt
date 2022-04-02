package com.preview.feature.market.domain.model

import org.joda.time.LocalDateTime

data class MarketItemMetal(
    val time: LocalDateTime,
    val metalName: String,
    val bid: Float,
    val ask: Float,
    val change: Float,
)
