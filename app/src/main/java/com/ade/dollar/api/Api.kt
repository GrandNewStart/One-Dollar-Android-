package com.ade.dollar.api

import com.ade.dollar.models.Item
import retrofit2.http.*

interface Api {

    @GET("/v1/forex/recent")
    suspend fun getExchangeRate(@Query("codes") key: String): List<Item>


}