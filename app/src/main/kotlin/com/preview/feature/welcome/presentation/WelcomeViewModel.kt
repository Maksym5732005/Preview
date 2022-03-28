package com.preview.feature.welcome.presentation

import androidx.lifecycle.MutableLiveData
import com.preview.base.BaseRxViewModel
import com.preview.base.LcenState
import com.preview.base.ThreadScheduler
import com.preview.base.delegate
import com.preview.base.mapDistinct
import com.preview.base.scheduleIoToUi
import com.preview.base.subscribeWithErrorLog
import com.preview.base.toLcenEventObservable
import com.preview.feature.welcome.domain.GetWelcomeItemsInteractor
import com.preview.feature.welcome.presentation.model.WelcomeItemUiEntity
import com.preview.feature.welcome.presentation.model.WelcomeViewState
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val getWelcome: GetWelcomeItemsInteractor,
    private val scheduler: ThreadScheduler,
) : BaseRxViewModel() {

    private val liveState = MutableLiveData(createInitialState())
    private var state by liveState.delegate()

    val items = liveState.mapDistinct(WelcomeViewState::items)

    init {
        getInitialData()
    }

    fun itemRequested(item: WelcomeItemUiEntity) {
        // TODO: not implemented
    }

    private fun getInitialData() {
        getWelcome()
            .toLcenEventObservable()
            .scheduleIoToUi(scheduler)
            .subscribeWithErrorLog { lcen ->
                state = state.copy(
                    items = lcen.mapToUiEntity()
                )
            }.autoDispose()
    }

    private fun createInitialState(): WelcomeViewState =
        WelcomeViewState(
            items = LcenState.Loading,
        )
}