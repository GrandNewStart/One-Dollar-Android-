package com.ade.dollar.activities.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ade.dollar.api.Api
import com.ade.dollar.config.Constants
import com.ade.dollar.config.Constants.retrofit
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val service = retrofit.create(Api::class.java)

    val isLoading = MutableLiveData(false)
    val price = MutableLiveData<String?>(null)
    val date = MutableLiveData<String?>(null)

    fun getPrice() {
        price.value?.let { return }
        isLoading.value = true
        viewModelScope.launch {
            service.getExchangeRate("FRX.KRWUSD").let { list ->
                price.value = list[0].basePrice
                date.value = Constants.getDate()
                isLoading.value = false
            }
        }
    }

}