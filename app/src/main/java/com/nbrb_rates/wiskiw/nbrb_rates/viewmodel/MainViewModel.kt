package com.nbrb_rates.wiskiw.nbrb_rates.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.nbrb_rates.wiskiw.nbrb_rates.repository.CurrencyRateRepository

class MainViewModel(context: Context) : ViewModel() {


    private val repository = CurrencyRateRepository(context)
    private val currencyRateList = repository.getRates()



}
