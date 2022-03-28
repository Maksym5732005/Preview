package com.preview.feature.welcome.domain

import com.preview.feature.welcome.domain.model.WelcomeItemModel
import io.reactivex.Single
import javax.inject.Inject

class GetWelcomeItemsInteractor @Inject constructor(
    private val repository: WelcomeRepository,
) {

    operator fun invoke(): Single<List<WelcomeItemModel>> {
        return repository.getItems()
    }
}