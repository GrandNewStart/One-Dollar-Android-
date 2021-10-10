package com.ade.dollar.config

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.concurrent.TaskRunner.Companion.logger

class Interceptor: Interceptor {

    @SuppressLint("DefaultLocale")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        Log.d("Network Interceptor", "=== HTTP NETWORK API CALL ===")
        Log.d("Network Interceptor", "  ---> URL: ${request.url}")
        Log.d("Network Interceptor", "  ---> HEADERS: ${request.headers}")
        Log.d("Network Interceptor", "  ---> BODY: ${request.body}")

        return chain.proceed(request)
    }
}