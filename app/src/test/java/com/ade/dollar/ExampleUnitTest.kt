package com.ade.dollar

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val cal = Calendar.getInstance(Locale.KOREA)
        val format = SimpleDateFormat("yyyyMMdd")
        cal.add(Calendar.DAY_OF_YEAR, -1)
        val result = format.format(cal.time)
        print("============ $result")
    }


}