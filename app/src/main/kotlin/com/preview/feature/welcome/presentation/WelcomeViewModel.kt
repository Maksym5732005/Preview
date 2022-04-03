package com.preview.feature.welcome.presentation

import androidx.lifecycle.MutableLiveData
import com.preview.base.BaseViewModel
import com.preview.base.DebugMessageEvent
import com.preview.base.LcenState
import com.preview.base.NavigationEvent
import com.preview.base.ThreadScheduler
import com.preview.base.extensions.delegate
import com.preview.base.extensions.mapDistinct
import com.preview.base.scheduleIoToUi
import com.preview.base.extensions.subscribeWithErrorLog
import com.preview.base.extensions.toLcenEventObservable
import com.preview.feature.welcome.domain.GetWelcomeItemsInteractor
import com.preview.feature.welcome.domain.model.WelcomeTitles
import com.preview.feature.welcome.presentation.model.WelcomeItemUiState
import com.preview.feature.welcome.presentation.model.WelcomeUiState
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val getWelcome: GetWelcomeItemsInteractor,
    private val scheduler: ThreadScheduler,
) : BaseViewModel() {

    private val liveState = MutableLiveData(createInitialState())
    private var state by liveState.delegate()

    val items = liveState.mapDistinct(WelcomeUiState::items)

    init {
        getInitialData()
    }

    fun itemRequested(item: WelcomeItemUiState) {
        event.value = when(item.title) {
            WelcomeTitles.Market.name -> NavigationEvent.NavigationDirection(WelcomeFragmentDirections.pushToMarkets())
            WelcomeTitles.Permission.name -> NavigationEvent.NavigationDirection(WelcomeFragmentDirections.pushToPermission())
            else -> DebugMessageEvent("Invalid direction ${item.title}")
        }
    }

    private fun getInitialData() {
        getWelcome()
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog { lcen ->
                state = state.copy(
                    items = lcen.mapToUiState()
                )
            }.autoDispose()
    }

    private fun createInitialState(): WelcomeUiState =
        WelcomeUiState(
            items = LcenState.Loading,
        )
}