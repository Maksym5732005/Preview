package com.preview.feature.market.data

import com.preview.base.data.Dto
import com.preview.feature.market.domain.model.MarketItemMetal
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat

private const val TIME_STAMP_FORMAT = "yyyy-MM-dd HH:mm:ss"

data class PreciousMetalsNetworkEntity(
    val changePercentage: Float,
    val high: Float,
    val low: Float,
    val symbol: String,
    val currency: String,
    val ask: Float,
    val mid: Float,
    val change: String,
    val unit: String,
    val bid: Float,
    val timestamp: String,
) : Dto<MarketItemMetal> {

    override fun convert(): MarketItemMetal {
        return MarketItemMetal(
            time = LocalDateTime.parse(timestamp, DateTimeFormat.forPattern(TIME_STAMP_FORMAT)),
            metalName = symbol.convertSymbolToName(),
            bid = bid,
            ask = ask,
            change = changePercentage,
        )
    }
}

private fun String.convertSymbolToName(): String {
    return when (this) {
        "AU" -> "Gold"
        "AG" -> "Silver"
        "PT" -> "Platinum"
        "PD" -> "Palladium"
        "RH" -> "Rhodium"
        else -> throw IllegalStateException("Can't convert `$this` to name")
    }
}