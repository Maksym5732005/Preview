package com.preview.feature.market.domain.interactor

import com.preview.feature.market.domain.MarketRepository
import com.preview.feature.market.domain.model.MarketItemMetal
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class BaseMetalsInteractor @Inject constructor(
    private val marketRepository: MarketRepository,
) {

    fun fetch(skipCache: Boolean): Completable {
        return marketRepository.fetchBaseMetals(skipCache)
    }

    fun getLive(): Observable<List<MarketItemMetal>> {
        return marketRepository.getBaseMetalsLive()
    }
}