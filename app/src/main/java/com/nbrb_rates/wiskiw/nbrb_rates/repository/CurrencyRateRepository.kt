package com.nbrb_rates.wiskiw.nbrb_rates.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.nbrb_rates.wiskiw.nbrb_rates.model.CurrencyRate
import com.nbrb_rates.wiskiw.nbrb_rates.repository.networking.NbrbNetworkService

class CurrencyRateRepository(context: Context) {

    val nbrbNetworkService = NbrbNetworkService(context)

    fun getRates(): LiveData<List<CurrencyRate>> {
        val data = MutableLiveData<List<CurrencyRate>>()
        // nbrbNetworkService.loadCurrencyRates()
        return data
    }


}