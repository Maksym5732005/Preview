package com.preview.base.domain

import com.preview.feature.market.data.entity.BaseMetalsNetworkEntity
import com.preview.feature.market.data.entity.IndicesNetworkEntity
import com.preview.feature.market.data.entity.MarketStateNetworkEntity
import com.preview.feature.market.data.entity.PreciousMetalsNetworkEntity
import com.preview.feature.welcome.data.WelcomeItemNetworkEntity
import io.reactivex.Single

interface PreviewApi {
    fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>>
    fun getMarketState(): Single<MarketStateNetworkEntity>
    fun getPreciousMetals(): Single<List<PreciousMetalsNetworkEntity>>
    fun getBaseMetals(): Single<List<BaseMetalsNetworkEntity>>
    fun getIndices(): Single<List<IndicesNetworkEntity>>
}