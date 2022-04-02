package com.preview.feature.market.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.preview.base.BaseViewModel
import com.preview.base.DebugMessageEvent
import com.preview.base.LcenState
import com.preview.base.ResourceReader
import com.preview.base.ThreadScheduler
import com.preview.base.extensions.delegate
import com.preview.base.extensions.isLoading
import com.preview.base.extensions.mapDistinct
import com.preview.base.extensions.subscribeWithErrorLog
import com.preview.base.extensions.toLcenEventObservable
import com.preview.base.scheduleIoToUi
import com.preview.feature.market.domain.MarketStateInteractor
import com.preview.feature.market.domain.PreciousMetalsInteractor
import com.preview.feature.market.presentation.epoxy.MarketEpoxyControllerCallbacks
import com.preview.feature.market.presentation.model.MarketInfoUiState
import com.preview.feature.market.presentation.model.MarketUiState
import com.preview.feature.market.presentation.model.PreciousUiState
import javax.inject.Inject

class MarketViewModel @Inject constructor(
    private val marketState: MarketStateInteractor,
    private val preciousMetals: PreciousMetalsInteractor,
    private val scheduler: ThreadScheduler,
    private val resourceReader: ResourceReader,
) : BaseViewModel(), MarketEpoxyControllerCallbacks {

    private val _viewState = MutableLiveData(createInitialState())
    private var state by _viewState.delegate()

    val viewState = _viewState.distinctUntilChanged()
    val loadingState = _viewState.mapDistinct { s ->
        s.marketStateItem.lcenState.isLoading()
                || s.preciousState.lcenState.isLoading()
    }

    init {
        event.value = DebugMessageEvent("Screen is under construction")

        observeMarketState()
        fetchMarketState(false)

        observePreciousMetals()
        fetchPreciousMetals(false)
    }

    //region MarketEpoxyControllerCallbacks
    //endregion

    fun refreshRequested() {
        fetchMarketState(true)
        fetchPreciousMetals(true)
    }

    private fun fetchMarketState(skipCache: Boolean) {
        marketState.fetch(skipCache)
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.copy(marketStateItem = state.marketStateItem.copy(lcenState = it))
            }
            .autoDispose()
    }

    private fun observeMarketState() {
        marketState.getLive()
            .toLcenEventObservable()
            .map { it.convertToUiState(resourceReader) }
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.copy(marketStateItem = state.marketStateItem.copy(marketItem = it))
            }.autoDispose()
    }

    private fun fetchPreciousMetals(skipCache: Boolean) {
        preciousMetals.fetch(skipCache)
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.copy(preciousState = state.preciousState.copy(lcenState = it))
            }
            .autoDispose()
    }

    private fun observePreciousMetals() {
        preciousMetals.getLive()
            .map { it.convertToUiState(resourceReader) }
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.copy(preciousState = state.preciousState.copy(preciousItems = it))
            }.autoDispose()
    }

    private fun createInitialState() = MarketUiState(
        marketStateItem = MarketInfoUiState(
            lcenState = LcenState.Loading,
            marketItem = LcenState.Loading,
        ),
        preciousState = PreciousUiState(
            lcenState = LcenState.Loading,
            preciousItems = LcenState.Loading,
        ),
        baseMetalsItems = emptyList(),
        indicesItems = emptyList(),
    )
}