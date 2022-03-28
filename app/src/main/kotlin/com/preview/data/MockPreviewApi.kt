package com.preview.data

import com.preview.PreviewApi
import com.preview.feature.welcome.data.WelcomeItemNetworkEntity
import io.reactivex.Single
import javax.inject.Inject

class MockPreviewApi @Inject constructor() : PreviewApi {

    override fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>> {
        return Single.just(
            listOf(
                WelcomeItemNetworkEntity(
                    title = "Market",
                    description = "Async data loading",
                ),
                WelcomeItemNetworkEntity(
                    title = "Permission",
                    description = "Permission request delegate example",
                ),
            )
        )
    }
}