package com.preview.data

import com.preview.PreviewApi
import com.preview.feature.welcome.data.WelcomeItemNetworkEntity
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MockPreviewApi @Inject constructor() : PreviewApi {

    override fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>> {
        return Single.timer(1, TimeUnit.SECONDS)
            .map {
                listOf(
                    WelcomeItemNetworkEntity(
                        title = "Markets",
                        description = "Async data loading",
                    )
                )
            }
    }
}