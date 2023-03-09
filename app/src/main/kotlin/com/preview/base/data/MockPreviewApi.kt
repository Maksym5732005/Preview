package com.preview.base.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.preview.base.domain.PreviewApi
import com.preview.feature.market.data.entity.BaseMetalsNetworkEntity
import com.preview.feature.market.data.entity.IndicesNetworkEntity
import com.preview.feature.market.data.entity.MarketStateNetworkEntity
import com.preview.feature.market.data.entity.PreciousMetalsNetworkEntity
import com.preview.feature.welcome.data.entity.WelcomeItemNetworkEntity
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MockPreviewApi @Inject constructor() : PreviewApi {

    override fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>> {
        val type = object : TypeToken<Collection<WelcomeItemNetworkEntity>>() {}.type
        return Single.just(Gson().fromJson(welcomeItemsJson, type))
    }

    override fun getMarketState(): Single<MarketStateNetworkEntity> {
        return Single.just(Gson().fromJson(marketStatus, MarketStateNetworkEntity::class.java))
            .delay(3, TimeUnit.SECONDS)
    }

    override fun getPreciousMetals(): Single<List<PreciousMetalsNetworkEntity>> {
        val type = object : TypeToken<Collection<PreciousMetalsNetworkEntity>>() {}.type
        return Single.just<List<PreciousMetalsNetworkEntity>?>(Gson().fromJson(preciousMetals, type))
            .delay(6, TimeUnit.SECONDS)
    }

    override fun getBaseMetals(): Single<List<BaseMetalsNetworkEntity>> {
        val type = object : TypeToken<Collection<BaseMetalsNetworkEntity>>() {}.type
        return Single.just<List<BaseMetalsNetworkEntity>?>(Gson().fromJson(baseMetals, type))
            .delay(4, TimeUnit.SECONDS)
    }

    override fun getIndices(): Single<List<IndicesNetworkEntity>> {
        val type = object : TypeToken<Collection<IndicesNetworkEntity>>() {}.type
        return Single.just<List<IndicesNetworkEntity>?>(Gson().fromJson(indices, type))
            .delay(2, TimeUnit.SECONDS)
    }
}

private const val welcomeItemsJson = """
    [{
        "title": "Market",
        "description": "Async data loading"},
    {
        "title": "Permission",
        "description": "Permission request delegate example"
    }]
"""

private const val marketStatus = """
    {
        "status":"OPEN",
        "nextEvent":"Closes in 4 hrs. 32 mins"
    }
"""

private const val preciousMetals = """
    [{
        "changePercentage":-0.45,
        "high":1931.2,
        "low":1889.1,
        "symbol":"AU",
        "currency":"USD",
        "ask":1914.9,
        "mid":1914.4,
        "change":-8.7,
        "unit":"OUNCE",
        "bid":1913.9,
        "timestamp":"2022-03-29 12:28:39"
    },{
        "changePercentage":-1.3,
        "high":25.1,
        "low":23.93,
        "symbol":"AG",
        "currency":"USD",
        "ask":24.68,
        "mid":24.63,
        "change":-0.32,
        "unit":"OUNCE",
        "bid":24.58,
        "timestamp":"2022-03-29 12:28:40"
    },{
        "changePercentage":-0.91,
        "high":997,
        "low":952,
        "symbol":"PT",
        "currency":"USD",
        "ask":985,
        "mid":980,
        "change":-9,
        "unit":"OUNCE",
        "bid":975,
        "timestamp":"2022-03-29 12:28:43"
    },{
        "changePercentage":-5.83,
        "high":2386,
        "low":1960,
        "symbol":"PD",
        "currency":"USD",
        "ask":2186,
        "mid":2111,
        "change":-126,
        "unit":"OUNCE",
        "bid":2036,
        "timestamp":"2022-03-29 12:28:42"
    },{
        "changePercentage":0,
        "high":19950,
        "low":17950,
        "symbol":"RH",
        "currency":"USD",
        "ask":19950,
        "mid":18950,
        "change":0,
        "unit":"OUNCE",
        "bid":17950,
        "timestamp":"2022-03-29 08:20:20"
    }]
"""

private const val baseMetals = """
    [{
        "changePercentage":0.2,
        "high":4.6964,
        "low":4.6468,
        "symbol":"CU",
        "ask":4.6868,
        "Mid":4.6864,
        "change":0.0094,
        "unit":"POUND",
        "bid":4.6859,
        "timestamp":"2022-03-29 12:28:42"
    },{
        "changePercentage":-0.19,
        "high":15.0392,
        "low":14.1347,
        "symbol":"NI",
        "ask":14.5059,
        "Mid":14.4537,
        "change":-0.028,
        "unit":"POUND",
        "bid":14.4016,
        "timestamp":"2022-03-29 12:27:43"
    },{
        "changePercentage":-4.3,
        "high":1.7538,
        "low":1.6379,
        "symbol":"AL",
        "ask":1.657,
        "Mid":1.6566,
        "change":-0.0744,
        "unit":"POUND",
        "bid":1.6563,
        "timestamp":"2022-03-29 12:26:43"
    },{
        "changePercentage":-0.59,
        "high":1.8629,
        "low":1.8308,
        "symbol":"ZN",
        "ask":1.836,
        "Mid":1.8352,
        "change":-0.0109,
        "unit":"POUND",
        "bid":1.8344,
        "timestamp":"2022-03-29 12:27:43"
    },{
        "changePercentage":0.96,
        "high":1.0869,
        "low":1.0713,
        "symbol":"PB",
        "ask":1.0851,
        "Mid":1.0846,
        "change":0.0104,
        "unit":"POUND",
        "bid":1.0842,
        "timestamp":"2022-03-29 12:22:41"
    },{
        "changePercentage":4.55,
        "high":57.5,
        "low":57.5,
        "symbol":"UR",
        "ask":57.5,
        "Mid":57.5,
        "change":2.5,
        "unit":"POUND",
        "bid":57.5,
        "timestamp":"2022-03-21 00:00:00"
    }]
"""

private const val indices = """
    [{
        "changePercentage":-3.12,
        "price":102.65,
        "symbol":"CL",
        "change":-3.31,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":0.54,
        "price":35144.26,
        "symbol":"DJI",
        "change":188.37,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":1.19,
        "price":14526.01,
        "symbol":"IXIC",
        "change":171.11,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":0.65,
        "price":4605.13,
        "symbol":"SPX",
        "change":29.61,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":-0.7,
        "price":98.39,
        "symbol":"USDX",
        "change":-0.69,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":0.68,
        "price":16913.17,
        "symbol":"NYA",
        "change":114.45,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":-0,
        "price":21977.33,
        "symbol":"GSPTSE",
        "change":-0.5,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":1.1,
        "price":28252.42,
        "symbol":"N225",
        "change":308.53,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":-0.47,
        "price":155.43,
        "symbol":"XAU",
        "change":-0.73,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":-0.5,
        "price":305.24,
        "symbol":"HUI",
        "change":-1.54,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":-0.73,
        "price":342.11,
        "symbol":"SPTTGD",
        "change":-2.5,
        "timestamp":"2022-03-29 12:28:35"
    },{
        "changePercentage":0.89,
        "price":308.76,
        "symbol":"GFMS",
        "change":2.72,
        "timestamp":"2012-03-16 00:00:00"
    }]
"""