package com.nbrb_rates.wiskiw.nbrb_rates.repository.networking

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import org.xmlpull.v1.XmlPullParser

class NbrbNetworkService(val context: Context) {

    companion object {
        private const val RATES_URL = "http://www.nbrb.by/Services/XmlExRates.aspx"
    }

    // Make get request to rate's endpoint
    fun loadCurrencyRates(listener: Response.Listener<XmlPullParser>,
                          errorListener: Response.ErrorListener) {
        XmlPullRequest(RATES_URL, listener, errorListener).let {
            val queue = Volley.newRequestQueue(context)
            queue.add(it)
        }
    }

}