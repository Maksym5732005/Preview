package com.preview.feature.welcome.data

import com.preview.PreviewApi
import com.preview.base.convert
import com.preview.feature.welcome.domain.WelcomeRepository
import com.preview.feature.welcome.domain.model.WelcomeItemModel
import io.reactivex.Single
import javax.inject.Inject

class WelcomeRepositoryImpl @Inject constructor(
    private val api: PreviewApi,
) : WelcomeRepository {

    override fun getItems(): Single<List<WelcomeItemModel>> {
        return api.getWelcomeItems().map { it.convert() }
    }
}