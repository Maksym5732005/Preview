package com.preview.feature.market

import com.preview.base.AppMemory
import com.preview.base.reactiveCache
import com.preview.feature.market.domain.model.MarketInfo
import io.reactivex.Observable
import javax.inject.Inject

interface MarketStorage {
    fun isMarketStateEmpty(): Boolean
    fun getMarketStateLive(): Observable<MarketInfo>
    fun setMarketState(market: MarketInfo)
}

private const val MARKET_INFO_KEY = "market_info_key"

class MarketMemoryStorage @Inject constructor(
    memory: AppMemory
) : MarketStorage {

    private val marketState by memory.reactiveCache<MarketInfo>()

    override fun isMarketStateEmpty(): Boolean {
        return marketState.isEmpty()
    }

    override fun getMarketStateLive(): Observable<MarketInfo> {
        return marketState.getLive(MARKET_INFO_KEY)
    }

    override fun setMarketState(market: MarketInfo) {
        marketState[MARKET_INFO_KEY] = market
    }
}