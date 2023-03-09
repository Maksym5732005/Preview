package com.preview.feature.welcome.data

import com.preview.feature.welcome.data.entity.WelcomeItemNetworkEntity
import io.reactivex.Single

interface WelcomeApi {
    fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>>
}