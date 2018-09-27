package com.nbrb_rates.wiskiw.nbrb_rates.repository.networking

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.nio.charset.Charset


class XmlPullRequest(url: String,
                     private val listener: Response.Listener<XmlPullParser>,
                     errorListener: Response.ErrorListener) :
        Request<XmlPullParser>(Method.GET, url, errorListener) {

    override fun parseNetworkResponse(response: NetworkResponse?): Response<XmlPullParser> {
        return try {
            val factory = XmlPullParserFactory.newInstance()
            val pullParser = factory.newPullParser()

            val xmlString = String(response?.data ?: ByteArray(0),
                    Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))

            pullParser.setInput(StringReader(xmlString))

            return Response.success(pullParser, HttpHeaderParser.parseCacheHeaders(response))

        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }

    override fun deliverResponse(response: XmlPullParser) = listener.onResponse(response)
}