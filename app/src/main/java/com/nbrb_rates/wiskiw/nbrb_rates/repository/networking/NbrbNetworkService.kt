package com.nbrb_rates.wiskiw.nbrb_rates.repository.networking

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.Volley

class NbrbNetworkService(val context: Context) {

    companion object {
        private const val RATES_URL = "http://www.nbrb.by/Services/XmlExRates.aspx"
    }

    fun loadCurrencyRates() {
        XmlDocRequest(RATES_URL, Response.Listener {

        }, Response.ErrorListener {

        }).let {
            // Add the request to the RequestQueue.
            val queue = Volley.newRequestQueue(context)
            queue.add(it)
        }
    }

}