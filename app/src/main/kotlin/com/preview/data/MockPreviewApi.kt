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
        "Status":"OPEN",
        "NextEvent":"Closes in 4 hrs. 32 mins"
    }
"""

private const val preciousMetals = """
    [{
        "ChangePercentage":-0.45,
        "High":1931.2,
        "Low":1889.1,
        "Symbol":"AU",
        "Currency":"USD",
        "Ask":1914.9,
        "Mid":1914.4,
        "Change":-8.7,
        "Unit":"OUNCE",
        "Bid":1913.9,
        "Timestamp":"2022-03-29 12:28:39"
    },{
        "ChangePercentage":-1.3,
        "High":25.1,
        "Low":23.93,
        "Symbol":"AG",
        "Currency":"USD",
        "Ask":24.68,
        "Mid":24.63,
        "Change":-0.32,
        "Unit":"OUNCE",
        "Bid":24.58,
        "Timestamp":"2022-03-29 12:28:40"
    },{
        "ChangePercentage":-0.91,
        "High":997,
        "Low":952,
        "Symbol":"PT",
        "Currency":"USD",
        "Ask":985,
        "Mid":980,
        "Change":-9,
        "Unit":"OUNCE",
        "Bid":975,
        "Timestamp":"2022-03-29 12:28:43"
    },{
        "ChangePercentage":-5.83,
        "High":2386,
        "Low":1960,
        "Symbol":"PD",
        "Currency":"USD",
        "Ask":2186,
        "Mid":2111,
        "Change":-126,
        "Unit":"OUNCE",
        "Bid":2036,
        "Timestamp":"2022-03-29 12:28:42"
    },{
        "ChangePercentage":0,
        "High":19950,
        "Low":17950,
        "Symbol":"RH",
        "Currency":"USD",
        "Ask":19950,
        "Mid":18950,
        "Change":0,
        "Unit":"OUNCE",
        "Bid":17950,
        "Timestamp":"2022-03-29 08:20:20"
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