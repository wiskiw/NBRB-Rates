package com.nbrb_rates.wiskiw.nbrb_rates.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.nbrb_rates.wiskiw.nbrb_rates.model.CurrencyRate
import com.nbrb_rates.wiskiw.nbrb_rates.model.CurrencyRateListWrapper
import com.nbrb_rates.wiskiw.nbrb_rates.repository.networking.NbrbNetworkService
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException


class CurrencyRateRepository(context: Context) {

    companion object {
        private const val LOG_TAG = "NBRB-CRR"

        private const val XML_CURRENCY_ITEM_TAG = "Currency"
        private const val XML_ID_ATTRIBUTE_NAME = "Id"
        private const val XML_NUM_CODE_TAG = "NumCode"
        private const val XML_CHAR_CODE_TAG = "CharCode"
        private const val XML_SCALE_TAG = "Scale"
        private const val XML_NAME_TAG = "Name"
        private const val XML_RATE_TAG = "Rate"
    }

    private val nbrbNetworkService = NbrbNetworkService(context)

    // Receiving currency rate list
    fun getRates(): LiveData<CurrencyRateListWrapper> {
        val ratesResult = MutableLiveData<CurrencyRateListWrapper>()

        nbrbNetworkService.loadCurrencyRates(Response.Listener { pullParser ->
            ratesResult.value = CurrencyRateListWrapper()
            try {
                ratesResult.value?.data = parseXml(pullParser)

                // Check if any rates found
                val noOneFoundEx = IOException("No one currency rates wasn't loaded/found")
                ratesResult.value?.data?.let {
                    if (it.isEmpty()) {
                        ratesResult.value?.error = noOneFoundEx
                    }
                } ?: run {
                    ratesResult.value?.error = noOneFoundEx
                }

            } catch (e: XmlPullParserException) {
                ratesResult.value?.error = e
            }

        }, Response.ErrorListener {
            Log.w(LOG_TAG, it.localizedMessage, it)
            ratesResult.value?.error = it
        })

        return ratesResult
    }

    @Throws(XmlPullParserException::class)
    private fun parseXml(pullParser: XmlPullParser): ArrayList<CurrencyRate> {
        val rateList = arrayListOf<CurrencyRate>()
        var rateItem: CurrencyRate? = null

        var event = pullParser.eventType
        var tagKey: String?

        while (event != XmlPullParser.END_DOCUMENT) {
            tagKey = pullParser.name
            when (event) {
                XmlPullParser.START_TAG -> {

                    // Create new CurrencyRate object on every "Currency" xml tag
                    if (tagKey == XML_CURRENCY_ITEM_TAG) rateItem = CurrencyRate(
                            pullParser.getAttributeValue(null,
                                    XML_ID_ATTRIBUTE_NAME).toInt()
                    )

                    // Filling CurrencyRate object's fields
                    rateItem?.let {
                        when (tagKey) {
                            XML_NUM_CODE_TAG -> it.numCode = pullParser.nextText().toInt()
                            XML_CHAR_CODE_TAG -> it.charCode = pullParser.nextText()
                            XML_SCALE_TAG -> it.scale = pullParser.nextText().toInt()
                            XML_NAME_TAG -> it.name = pullParser.nextText()
                            XML_RATE_TAG -> it.rate = pullParser.nextText().toDouble()
                        }
                    }
                }

                // Add CurrencyRate object to list when "Currency" xml tag closed
                XmlPullParser.END_TAG -> {
                    if (rateItem != null && tagKey == XML_CURRENCY_ITEM_TAG) {
                        rateList.add(rateItem)
                    }
                }
            }
            event = pullParser.next()
        }
        return rateList
    }

}