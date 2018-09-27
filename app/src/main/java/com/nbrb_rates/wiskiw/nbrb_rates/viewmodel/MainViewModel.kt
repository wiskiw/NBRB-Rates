package com.nbrb_rates.wiskiw.nbrb_rates.viewmodel

import android.arch.lifecycle.ViewModel
import com.nbrb_rates.wiskiw.nbrb_rates.repository.CurrencyRateRepository

class MainViewModel : ViewModel() {

    fun debug(repository: CurrencyRateRepository) {

        val currencyRateList = repository.getRates()

    }


}
