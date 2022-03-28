package com.preview.feature.welcome.presentation

import androidx.lifecycle.MutableLiveData
import com.preview.base.BaseRxViewModel
import com.preview.base.LcenState
import com.preview.base.delegate
import com.preview.base.mapDistinct
import com.preview.feature.welcome.presentation.model.WelcomeViewState

internal class WelcomeViewModel: BaseRxViewModel() {

    private val liveState = MutableLiveData(createInitialState())
    private val state by liveState.delegate()

    val items = liveState.mapDistinct(WelcomeViewState::items)

    private fun createInitialState(): WelcomeViewState =
        WelcomeViewState(
            items = LcenState.Loading,
        )
}