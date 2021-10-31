package com.dakuo


import com.google.gson.Gson
import okhttp3.*


class OkService {
    fun getDetail(symbol: String): OkResultBean? {
        val client = OkHttpClient()
        val request = Request.Builder().url("https://www.okex.com/api/v5/market/ticker?instId=$symbol").get().build()
        val call = client.newCall(request)
        val string = call.execute().body?.string()
        return Gson().fromJson(string, OkResultBean::class.java)
    }

    data class OkResultBean(
        val code: String,
        val msg: String,
        val data: List<Okticker>
    )

    data class Okticker(
       val instType: String,
       val instId: String,
       val last: Double, //最新成交价
       val lastSz: String, //最新成交的数量
       val askPx: String, //卖一价
       val askSz: String, //卖一价的挂单数数量
       val bidPx: String, //买一价
       val bidSz: String,//买一价的挂单数量
       val open24h: Double,//24小时开盘价
       val high24h: String,//24小时最高价
       val low24h: String,//24小时最低价
       val volCcy24h: String,//	24小时成交量，以币为单位
       val vol24h: String,//24小时成交量，以张为单位
       val sodUtc0: String,//UTC 0 时开盘价
       val sodUtc8: String,//UTC+8 时开盘价
       val ts: String//ticker数据产生时间，Unix时间戳的毫秒数格式，如 1597026383085
    )
}



