package com.preview.feature.welcome.presentation

import androidx.lifecycle.MutableLiveData
import com.preview.base.BaseRxViewModel
import com.preview.base.LcenState
import com.preview.base.delegate
import com.preview.base.mapDistinct
import com.preview.base.toLcenEventObservable
import com.preview.feature.welcome.domain.GetWelcomeItemsInteractor
import com.preview.feature.welcome.presentation.model.WelcomeItemUiEntity
import com.preview.feature.welcome.presentation.model.WelcomeViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    private val getWelcome: GetWelcomeItemsInteractor,
) : BaseRxViewModel() {

    private val liveState = MutableLiveData(createInitialState())
    private var state by liveState.delegate()

    val items = liveState.mapDistinct(WelcomeViewState::items)

    init {
        getInitialData()
    }

    fun itemRequested(item: WelcomeItemUiEntity) {

    }

    private fun getInitialData() {
        getWelcome()
            .toLcenEventObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                state = state.copy(
                    items = it.mapToUiEntity()
                )
            }.autoDispose()
    }

    private fun createInitialState(): WelcomeViewState =
        WelcomeViewState(
            items = LcenState.Loading,
        )
}