package com.preview.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.preview.PreviewApi
import com.preview.feature.welcome.data.WelcomeItemNetworkEntity
import io.reactivex.Single
import javax.inject.Inject

class MockPreviewApi @Inject constructor() : PreviewApi {

    override fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>> {
        val type = object : TypeToken<Collection<WelcomeItemNetworkEntity>>() {}.type
        return Single.just(Gson().fromJson(welcomeItemsJson, type))
    }
}

private const val welcomeItemsJson = """[
    {
    "title": "Market",
    "description": "Async data loading"},
    {
    "title": "Permission",
    "description": "Permission request delegate example"
    }
    ]
"""