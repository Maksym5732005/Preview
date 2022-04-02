package com.preview.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.preview.PreviewApi
import com.preview.feature.market.data.MarketStateNetworkEntity
import com.preview.feature.market.data.PreciousMetalsNetworkEntity
import com.preview.feature.welcome.data.WelcomeItemNetworkEntity
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

class MockPreviewApi @Inject constructor() : PreviewApi {

    override fun getWelcomeItems(): Single<List<WelcomeItemNetworkEntity>> {
        val type = object : TypeToken<Collection<WelcomeItemNetworkEntity>>() {}.type
        return Single.just(Gson().fromJson(welcomeItemsJson, type))
    }

    override fun getMarketState(): Single<MarketStateNetworkEntity> {
        return Single.just(Gson().fromJson(marketStatus, MarketStateNetworkEntity::class.java))
            .delay(Random.nextLong(5), TimeUnit.SECONDS)
    }

    override fun getPreciousMetals(): Single<List<PreciousMetalsNetworkEntity>> {
        val type = object : TypeToken<Collection<PreciousMetalsNetworkEntity>>() {}.type
        return Single.just<List<PreciousMetalsNetworkEntity>?>(Gson().fromJson(preciousMetals, type))
            .delay(Random.nextLong(5), TimeUnit.SECONDS)
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
        "ChangePercentage":0.2,
        "High":4.6964,
        "Low":4.6468,
        "Symbol":"CU",
        "Ask":4.6868,
        "Mid":4.6864,
        "Change":0.0094,
        "Unit":"POUND",
        "Bid":4.6859,
        "Timestamp":"2022-03-29 12:28:42"
    },{
        "ChangePercentage":-0.19,
        "High":15.0392,
        "Low":14.1347,
        "Symbol":"NI",
        "Ask":14.5059,
        "Mid":14.4537,
        "Change":-0.028,
        "Unit":"POUND",
        "Bid":14.4016,
        "Timestamp":"2022-03-29 12:27:43"
    },{
        "ChangePercentage":-4.3,
        "High":1.7538,
        "Low":1.6379,
        "Symbol":"AL",
        "Ask":1.657,
        "Mid":1.6566,
        "Change":-0.0744,
        "Unit":"POUND",
        "Bid":1.6563,
        "Timestamp":"2022-03-29 12:26:43"
    },{
        "ChangePercentage":-0.59,
        "High":1.8629,
        "Low":1.8308,
        "Symbol":"ZN",
        "Ask":1.836,
        "Mid":1.8352,
        "Change":-0.0109,
        "Unit":"POUND",
        "Bid":1.8344,
        "Timestamp":"2022-03-29 12:27:43"
    },{
        "ChangePercentage":0.96,
        "High":1.0869,
        "Low":1.0713,
        "Symbol":"PB",
        "Ask":1.0851,
        "Mid":1.0846,
        "Change":0.0104,
        "Unit":"POUND",
        "Bid":1.0842,
        "Timestamp":"2022-03-29 12:22:41"
    },{
        "ChangePercentage":4.55,
        "High":57.5,
        "Low":57.5,
        "Symbol":"UR",
        "Ask":57.5,
        "Mid":57.5,
        "Change":2.5,
        "Unit":"POUND",
        "Bid":57.5,
        "Timestamp":"2022-03-21 00:00:00"
    }]
"""

private const val indices = """
    [{
        "ChangePercentage":-3.12,
        "Price":102.65,
        "Symbol":"CL",
        "Change":-3.31,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":0.54,
        "Price":35144.26,
        "Symbol":"DJI",
        "Change":188.37,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":1.19,
        "Price":14526.01,
        "Symbol":"IXIC",
        "Change":171.11,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":0.65,
        "Price":4605.13,
        "Symbol":"SPX",
        "Change":29.61,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":-0.7,
        "Price":98.39,
        "Symbol":"USDX",
        "Change":-0.69,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":0.68,
        "Price":16913.17,
        "Symbol":"NYA",
        "Change":114.45,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":-0,
        "Price":21977.33,
        "Symbol":"GSPTSE",
        "Change":-0.5,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":1.1,
        "Price":28252.42,
        "Symbol":"N225",
        "Change":308.53,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":-0.47,
        "Price":155.43,
        "Symbol":"XAU",
        "Change":-0.73,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":-0.5,
        "Price":305.24,
        "Symbol":"HUI",
        "Change":-1.54,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":-0.73,
        "Price":342.11,
        "Symbol":"SPTTGD",
        "Change":-2.5,
        "Timestamp":"2022-03-29 12:28:35"
    },{
        "ChangePercentage":0.89,
        "Price":308.76,
        "Symbol":"GFMS",
        "Change":2.72,
        "Timestamp":"2012-03-16 00:00:00"
    }]
"""