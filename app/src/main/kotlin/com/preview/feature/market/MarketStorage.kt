package com.preview.feature.market

import com.preview.base.AppMemory
import com.preview.base.reactiveCache
import com.preview.feature.market.domain.model.MarketInfo
import com.preview.feature.market.domain.model.MarketItemMetal
import io.reactivex.Observable
import javax.inject.Inject

interface MarketStorage {
    fun isMarketStateEmpty(): Boolean
    fun getMarketStateLive(): Observable<MarketInfo>
    fun setMarketState(market: MarketInfo)
    fun isPreciousEmpty(): Boolean
    fun getPreciousLive(): Observable<List<MarketItemMetal>>
    fun setPrecious(preciousMetals: List<MarketItemMetal>)
}

private const val MARKET_INFO_KEY = "market_info_key"

class MarketMemoryStorage @Inject constructor(
    memory: AppMemory,
) : MarketStorage {

    private val marketState by memory.reactiveCache<MarketInfo>()
    private val precious by memory.reactiveCache<MarketItemMetal>()

    override fun isMarketStateEmpty(): Boolean {
        return marketState.isEmpty()
    }

    override fun getMarketStateLive(): Observable<MarketInfo> {
        return marketState.getLive(MARKET_INFO_KEY)
    }

    override fun setMarketState(market: MarketInfo) {
        marketState[MARKET_INFO_KEY] = market
    }

    override fun isPreciousEmpty(): Boolean {
        return precious.isEmpty()
    }

    override fun getPreciousLive(): Observable<List<MarketItemMetal>> {
        return precious.getAllLive()
    }

    override fun setPrecious(preciousMetals: List<MarketItemMetal>) {
        precious.putAll(preciousMetals.associateBy { it.metalName })
    }
}