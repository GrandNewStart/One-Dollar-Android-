package com.ade.dollar.config

import android.annotation.SuppressLint
import android.content.Context
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*

object Constants {

    lateinit var retrofit: Retrofit

    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {
        val date = Calendar.getInstance(Locale.KOREA)
        val format = SimpleDateFormat("yyyy.MM.dd")
        return format.format(Date(date.timeInMillis))
    }

    @SuppressLint("SimpleDateFormat")
    fun getPreviousDate(days: Int): String {
        val cal = Calendar.getInstance(Locale.KOREA)
        val format = SimpleDateFormat("yyyyMMdd")
        cal.add(Calendar.DAY_OF_YEAR, -days)
        return format.format(cal.time)
    }

    fun px2dp(context: Context, px: Int): Int {
        val density = context.resources.displayMetrics.density
        val dp = px / density
        return dp.toInt()
    }

    fun dp2px(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        val px = dp * density
        return px.toInt()
    }

}