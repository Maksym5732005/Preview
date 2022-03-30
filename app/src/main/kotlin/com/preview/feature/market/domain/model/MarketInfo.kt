package com.preview.feature.market.domain.model

import org.joda.time.LocalDateTime

data class MarketInfo(
    val time: LocalDateTime = LocalDateTime.now(),
    val isMarketOpen: Boolean,
    val closeAt: String,
)