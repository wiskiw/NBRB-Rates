package com.nbrb_rates.wiskiw.nbrb_rates.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.nbrb_rates.wiskiw.nbrb_rates.repository.CurrencyRateRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = CurrencyRateRepository(app)
    val currencyRateList = repository.getRates()

}
