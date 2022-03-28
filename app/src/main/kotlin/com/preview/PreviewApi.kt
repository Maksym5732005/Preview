package com.preview

import com.preview.feature.welcome.data.WelcomeItemNetworkEntity
import io.reactivex.Single

interface PreviewApi {
    fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>>
}