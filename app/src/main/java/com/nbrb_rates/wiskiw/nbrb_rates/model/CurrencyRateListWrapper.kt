package com.nbrb_rates.wiskiw.nbrb_rates.model

class CurrencyRateListWrapper {

    enum class State {
        LOADING, ERROR, DONE
    }

    var data: List<CurrencyRate>? = null
        get() {
            return if (field == null) {
                emptyList()
            } else {
                field
            }
        }
        set(value) {
            state = State.DONE
            field = value
        }

    // store any errors
    var error: Exception? = null
        set(value) {
            state = State.ERROR
            field = value
        }

    // represent current data state
    var state: State = State.LOADING
}