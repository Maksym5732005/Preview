package com.preview.feature.market.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.preview.base.domain.LcenState
import com.preview.base.domain.ThreadScheduler
import com.preview.base.domain.scheduleIoToUi
import com.preview.base.extensions.delegate
import com.preview.base.extensions.isLoading
import com.preview.base.extensions.mapDistinct
import com.preview.base.extensions.subscribeWithErrorLog
import com.preview.base.extensions.toLcenEventObservable
import com.preview.base.presentation.BaseViewModel
import com.preview.base.presentation.DebugMessageEvent
import com.preview.base.presentation.ResourceReader
import com.preview.feature.market.domain.interactor.IndicesInteractor
import com.preview.feature.market.domain.interactor.BaseMetalsInteractor
import com.preview.feature.market.domain.interactor.MarketStateInteractor
import com.preview.feature.market.domain.interactor.PreciousMetalsInteractor
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
    // We have single loading state for screen. So we show loading as long as at least one of the elements is loading.
    val loadingState = _viewState.mapDistinct { marketState ->
        marketState.marketStateItem.lcenState.isLoading()
                || marketState.preciousState.lcenState.isLoading()
                || marketState.baseMetalsState.lcenState.isLoading()
                || marketState.indicesState.lcenState.isLoading()
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

    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Requesting update for Market state.
     * @param skipCache use `False` to get data from a local cache if available. `True` to request fresh data from remote.
     */
    private fun fetchMarketState(skipCache: Boolean) {
        marketState.fetch(skipCache)
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.updateMarketInfoLcenState(newState = it)
            }
            .autoDispose()
    }

    /**
     * Subscribe for Market state changes.
     */
    private fun observeMarketState() {
        marketState.getLive()
            .toLcenEventObservable()
            .map { it.convertToUiState(resourceReader) }
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.updateMarketInfoContent(it)
            }.autoDispose()
    }

    /**
     * Request update for Precious metals.
     * @param skipCache use `False` to get data from a local cache if available. `True` to request fresh data from remote.
     */
    private fun fetchPreciousMetals(skipCache: Boolean) {
        preciousMetals.fetch(skipCache)
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.updatePreciousMetalsLcenState(it)
            }
            .autoDispose()
    }

    /**
     * Subscribe for Precious metals changes.
     */
    private fun observePreciousMetals() {
        preciousMetals.getLive()
            .map { it.map(MarketItemMetal::convertToUiState) }
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.updatePreciousMetalsContent(it)
            }.autoDispose()
    }

    /**
     * Request update for Base metals.
     * @param skipCache use `False` to get data from a local cache if available. `True` to request fresh data from remote.
     */
    private fun fetchBaseMetals(skipCache: Boolean) {
        baseMetals.fetch(skipCache)
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.updateBaseMetalsLcenState(it)
            }
            .autoDispose()
    }

    /**
     * Subscribe for Base metals changes.
     */
    private fun observeBaseMetals() {
        baseMetals.getLive()
            .map { it.map(MarketItemMetal::convertToUiState) }
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.updateBaseMetalsContent(it)
            }.autoDispose()
    }

    /**
     * Request update for Indices.
     * @param skipCache use `False` to get data from a local cache if available. `True` to request fresh data from remote.
     */
    private fun fetchIndices(skipCache: Boolean) {
        indices.fetch(skipCache)
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.updateIndicesLcenState(it)
            }
            .autoDispose()
    }

    /**
     * Subscribe for Indices changes.
     */
    private fun observeIndices() {
        indices.getLive()
            .map { it.map(MarketItemIndex::convertToUiState) }
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog {
                state = state.updateIndicesContent(it)
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