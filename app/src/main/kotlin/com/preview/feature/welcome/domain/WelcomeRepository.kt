package com.preview.feature.welcome.domain

import com.preview.feature.welcome.domain.model.WelcomeItemModel
import io.reactivex.Single

interface WelcomeRepository {
    fun getItems(): Single<List<WelcomeItemModel>>
}