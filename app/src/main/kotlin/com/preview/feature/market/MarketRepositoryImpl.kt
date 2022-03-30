package com.preview.feature.market

import com.preview.PreviewApi
import com.preview.base.extensions.flatMapCompletableAction
import com.preview.feature.market.domain.MarketRepository
import com.preview.feature.market.domain.model.MarketInfo
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class MarketRepositoryImpl @Inject constructor(
    private val api: PreviewApi,
    private val memory: MarketStorage,
) : MarketRepository {

    override fun fetchMarketState(skipCache: Boolean): Completable {
        return Completable.defer {
            if (skipCache || memory.isMarketStateEmpty()) {
                api.getMarketState().map {
                    it.convert()
                }.flatMapCompletableAction(memory::setMarketState)
            } else {
                Completable.complete()
            }
        }
    }

    override fun getMarketStateLive(): Observable<MarketInfo> {
        return memory.getMarketStateLive()
    }
}