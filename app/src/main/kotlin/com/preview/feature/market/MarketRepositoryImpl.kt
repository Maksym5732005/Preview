package com.preview.feature.market

import com.preview.PreviewApi
import com.preview.base.extensions.flatMapCompletableAction
import com.preview.feature.market.data.BaseMetalsNetworkEntity
import com.preview.feature.market.data.PreciousMetalsNetworkEntity
import com.preview.feature.market.domain.MarketRepository
import com.preview.feature.market.domain.model.MarketInfo
import com.preview.feature.market.domain.model.MarketItemMetal
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

    override fun fetchPreciousMetals(skipCache: Boolean): Completable {
        return Completable.defer {
            if (skipCache || memory.isPreciousEmpty()) {
                api.getPreciousMetals().map { list ->
                    list.map(PreciousMetalsNetworkEntity::convert)
                }.flatMapCompletableAction(memory::setPrecious)
            } else {
                Completable.complete()
            }
        }
    }

    override fun getPreciousMetalsLive(): Observable<List<MarketItemMetal>> {
        return memory.getPreciousLive()
    }

    override fun fetchBaseMetals(skipCache: Boolean): Completable {
        return Completable.defer {
            if (skipCache || memory.isBaseEmpty()) {
                api.getBaseMetals().map { list ->
                    list.map(BaseMetalsNetworkEntity::convert)
                }.flatMapCompletableAction(memory::setBase)
            } else {
                Completable.complete()
            }
        }
    }

    override fun getBaseMetalsLive(): Observable<List<MarketItemMetal>> {
        return memory.getBaseLive()
    }
}