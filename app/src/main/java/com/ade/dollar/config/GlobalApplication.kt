package com.ade.dollar.config

import android.app.Application
import com.ade.dollar.R
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GlobalApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor())
            .build()
        val gson = GsonBuilder().setLenient().create()

        Constants.retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.api_url))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

}