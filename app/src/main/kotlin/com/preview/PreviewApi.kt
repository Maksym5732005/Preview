package com.preview

import com.preview.feature.market.MarketStateNetworkEntity
import com.preview.feature.welcome.data.WelcomeItemNetworkEntity
import io.reactivex.Single

interface PreviewApi {
    fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>>
    fun getMarketState(): Single<MarketStateNetworkEntity>
}