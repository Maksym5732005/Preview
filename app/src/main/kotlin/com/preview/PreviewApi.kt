package com.preview

import com.preview.feature.market.data.BaseMetalsNetworkEntity
import com.preview.feature.market.data.MarketStateNetworkEntity
import com.preview.feature.market.data.PreciousMetalsNetworkEntity
import com.preview.feature.welcome.data.WelcomeItemNetworkEntity
import io.reactivex.Single

interface PreviewApi {
    fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>>
    fun getMarketState(): Single<MarketStateNetworkEntity>
    fun getPreciousMetals(): Single<List<PreciousMetalsNetworkEntity>>
    fun getBaseMetals(): Single<List<BaseMetalsNetworkEntity>>
}