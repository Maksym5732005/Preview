package com.preview.feature.market.domain

import com.preview.feature.market.domain.model.MarketItemMetal
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class PreciousMetalsInteractor @Inject constructor(
    private val marketRepository: MarketRepository,
) {

    fun fetch(skipCache: Boolean): Completable {
        return marketRepository.fetchPreciousMetals(skipCache)
    }

    fun getLive(): Observable<List<MarketItemMetal>> {
        return marketRepository.getPreciousMetalsLive()
    }
}