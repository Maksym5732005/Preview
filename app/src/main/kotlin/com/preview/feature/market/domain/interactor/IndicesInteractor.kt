package com.preview.feature.market.domain.interactor

import com.preview.feature.market.domain.MarketRepository
import com.preview.feature.market.domain.model.MarketItemIndex
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class IndicesInteractor @Inject constructor(
    private val marketRepository: MarketRepository,
) {

    fun fetch(skipCache: Boolean): Completable {
        return marketRepository.fetchIndices(skipCache)
    }

    fun getLive(): Observable<List<MarketItemIndex>> {
        return marketRepository.getIndicesLive()
    }
}