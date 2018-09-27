package com.nbrb_rates.wiskiw.nbrb_rates.model

class CurrencyRateListWrapper {

    var data: List<CurrencyRate>? = null
        get() {
            return if (field == null) {
                emptyList()
            } else {
                field
            }
        }

    var error: Exception? = null

}