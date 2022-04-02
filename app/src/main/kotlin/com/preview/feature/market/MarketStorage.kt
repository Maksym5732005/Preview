package com.preview.feature.market

import com.preview.base.AppMemory
import com.preview.base.reactiveCache
import com.preview.feature.market.domain.model.MarketInfo
import com.preview.feature.market.domain.model.MarketItemIndex
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
    fun isBaseEmpty(): Boolean
    fun getBaseLive(): Observable<List<MarketItemMetal>>
    fun setBase(preciousMetals: List<MarketItemMetal>)
    fun isIndicesEmpty(): Boolean
    fun getIndicesLive(): Observable<List<MarketItemIndex>>
    fun setIndices(preciousMetals: List<MarketItemIndex>)
}

private const val MARKET_INFO_KEY = "market_info_key"

class MarketMemoryStorage @Inject constructor(
    memory: AppMemory,
) : MarketStorage {

    private val marketState by memory.reactiveCache<MarketInfo>()
    private val precious by memory.reactiveCache<MarketItemMetal>()
    private val base by memory.reactiveCache<MarketItemMetal>()
    private val indices by memory.reactiveCache<MarketItemIndex>()

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

    override fun isBaseEmpty(): Boolean {
        return base.isEmpty()
    }

    override fun getBaseLive(): Observable<List<MarketItemMetal>> {
        return base.getAllLive()
    }

    override fun setBase(preciousMetals: List<MarketItemMetal>) {
        base.putAll(preciousMetals.associateBy { it.metalName })
    }

    override fun isIndicesEmpty(): Boolean {
        return indices.isEmpty()
    }

    override fun getIndicesLive(): Observable<List<MarketItemIndex>> {
        return indices.getAllLive()
    }

    override fun setIndices(preciousMetals: List<MarketItemIndex>) {
        indices.putAll(preciousMetals.associateBy { it.indexName })
    }
}