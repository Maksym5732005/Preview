package com.preview.feature.market.data.entity

import com.preview.base.data.Dto
import com.preview.feature.market.domain.model.MarketInfo
import org.joda.time.LocalDateTime

data class MarketStateNetworkEntity(
    val status: Status,
    val nextEvent: String,
) : Dto<MarketInfo> {

    override fun convert(): MarketInfo {
        return MarketInfo(
            time = LocalDateTime.now(),
            isMarketOpen = status == Status.OPEN,
            closeAt = nextEvent,
        )
    }
}

@Suppress("unused")
enum class Status { OPEN, CLOSE }