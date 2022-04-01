package com.preview.feature.market.domain

import com.preview.feature.market.domain.model.MarketInfo
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class MarketStateInteractor @Inject constructor(
    private val marketRepository: MarketRepository,
) {

    fun fetch(skipCache: Boolean): Completable {
        return marketRepository.fetchMarketState(skipCache)
    }

    fun getLive(): Observable<MarketInfo> {
        return marketRepository.getMarketStateLive()
    }
}