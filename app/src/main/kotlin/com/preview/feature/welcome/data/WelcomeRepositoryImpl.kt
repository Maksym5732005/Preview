package com.preview.feature.welcome.data

import com.preview.feature.welcome.domain.WelcomeRepository
import com.preview.feature.welcome.domain.model.WelcomeItemModel
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WelcomeRepositoryImpl @Inject constructor(

) : WelcomeRepository {
    override fun getItems(): Single<List<WelcomeItemModel>> {
        return Single.timer(2, TimeUnit.SECONDS)
            .map {
                listOf(WelcomeItemModel("Title1", "description"))
            }
    }
}