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
import com.preview.feature.market.domain.BaseMetalsInteractor
import com.preview.feature.market.domain.IndicesInteractor
import com.preview.feature.market.domain.MarketStateInteractor
import com.preview.feature.market.domain.PreciousMetalsInteractor
import com.preview.feature.market.domain.model.MarketItemIndex
import com.preview.feature.market.domain.model.MarketItemMetal
import com.preview.feature.market.presentation.epoxy.MarketEpoxyControllerCallbacks
import com.preview.feature.market.presentation.model.BaseUiState
import com.preview.feature.market.presentation.model.IndicesUiState
import com.preview.feature.market.presentation.model.MarketInfoUiState
import com.preview.feature.market.presentation.model.MarketUiState
import com.preview.feature.market.presentation.model.PreciousUiState
import javax.inject.Inject

class MarketViewModel @Inject constructor(
    private val marketState: MarketStateInteractor,
    private val preciousMetals: PreciousMetalsInteractor,
    private val baseMetals: BaseMetalsInteractor,
    private val indices: IndicesInteractor,
    private val scheduler: ThreadScheduler,
    private val resourceReader: ResourceReader,
) : BaseViewModel(), MarketEpoxyControllerCallbacks {

    private val _viewState = MutableLiveData(createInitialState())
    private var state by _viewState.delegate()

    val viewState = _viewState.distinctUntilChanged()
    val loadingState = _viewState.mapDistinct { s ->
        s.marketStateItem.lcenState.isLoading()
                || s.preciousState.lcenState.isLoading()
                || s.baseMetalsState.lcenState.isLoading()
                || s.indicesState.lcenState.isLoading()
    }

    init {
        observeMarketState()
        fetchMarketState(false)

        observePreciousMetals()
        fetchPreciousMetals(false)

        observeBaseMetals()
        fetchBaseMetals(false)

        observeIndices()
        fetchIndices(false)
    }

    //region MarketEpoxyControllerCallbacks
    override fun metalClicked(metal: String) {
        event.value = DebugMessageEvent(metal)
    }

    override fun indexClicked(index: String) {
        event.value = DebugMessageEvent(index)
    }
    //endregion

    fun refreshRequested() {
        fetchMarketState(true)
        fetchPreciousMetals(true)
        fetchBaseMetals(true)
        fetchIndices(true)
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
            .map { it.map(MarketItemMetal::convertToUiState) }
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.copy(preciousState = state.preciousState.copy(preciousItems = it))
            }.autoDispose()
    }

    private fun fetchBaseMetals(skipCache: Boolean) {
        baseMetals.fetch(skipCache)
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.copy(baseMetalsState = state.baseMetalsState.copy(lcenState = it))
            }
            .autoDispose()
    }

    private fun observeBaseMetals() {
        baseMetals.getLive()
            .map { it.map(MarketItemMetal::convertToUiState) }
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.copy(baseMetalsState = state.baseMetalsState.copy(preciousItems = it))
            }.autoDispose()
    }

    private fun fetchIndices(skipCache: Boolean) {
        indices.fetch(skipCache)
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.copy(indicesState = state.indicesState.copy(lcenState = it))
            }
            .autoDispose()
    }

    private fun observeIndices() {
        indices.getLive()
            .map { it.map(MarketItemIndex::convertToUiState) }
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.copy(indicesState = state.indicesState.copy(indicesItems = it))
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
        baseMetalsState = BaseUiState(
            lcenState = LcenState.Loading,
            preciousItems = LcenState.Loading,
        ),
        indicesState = IndicesUiState(
            lcenState = LcenState.Loading,
            indicesItems = LcenState.Loading,
        ),
    )
}