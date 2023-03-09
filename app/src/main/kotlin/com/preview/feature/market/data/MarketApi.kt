package com.preview.feature.market.data

import com.preview.feature.market.data.entity.BaseMetalsNetworkEntity
import com.preview.feature.market.data.entity.IndicesNetworkEntity
import com.preview.feature.market.data.entity.MarketStateNetworkEntity
import com.preview.feature.market.data.entity.PreciousMetalsNetworkEntity
import io.reactivex.Single

interface MarketApi {
    fun getMarketState(): Single<MarketStateNetworkEntity>
    fun getPreciousMetals(): Single<List<PreciousMetalsNetworkEntity>>
    fun getBaseMetals(): Single<List<BaseMetalsNetworkEntity>>
    fun getIndices(): Single<List<IndicesNetworkEntity>>
}