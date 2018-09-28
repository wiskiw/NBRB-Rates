package com.nbrb_rates.wiskiw.nbrb_rates.repository.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.Volley
import org.xmlpull.v1.XmlPullParser

class NbrbNetworkService(val context: Context) {

    companion object {
        private const val RATES_URL = "http://www.nbrb.by/Services/XmlExRates.aspx"
    }

    // Make get request to rate's endpoint
    fun loadCurrencyRates(listener: Response.Listener<XmlPullParser>,
                          errorListener: Response.ErrorListener) {
        if (!isNetworkAvailable()) {
            val networkError = VolleyError("No network connection")
            errorListener.onErrorResponse(networkError)
        } else XmlPullRequest(RATES_URL, listener, errorListener).let {
            val queue = Volley.newRequestQueue(context)
            queue.add(it)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


}