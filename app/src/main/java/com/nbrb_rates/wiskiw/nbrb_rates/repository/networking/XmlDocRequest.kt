package com.nbrb_rates.wiskiw.nbrb_rates.repository.networking

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.StringReader
import java.nio.charset.Charset
import javax.xml.parsers.DocumentBuilderFactory


class XmlDocRequest(url: String,
                    private val listener: Response.Listener<Document>,
                    errorListener: Response.ErrorListener) :
        Request<Document>(Method.GET, url, errorListener) {

    override fun parseNetworkResponse(response: NetworkResponse?): Response<Document> {
        return try {
            val xmlString = String(response?.data ?: ByteArray(0),
                    Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))

            val inputSource = InputSource()
            inputSource.characterStream = StringReader(xmlString)

            val db = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            return Response.success(db.parse(inputSource),
                    HttpHeaderParser.parseCacheHeaders(response))


            /*
            catching:
                - ParserConfigurationException
                - SAXException
                - IOException
                - UnsupportedEncodingException
             */
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }

    override fun deliverResponse(response: Document) = listener.onResponse(response)
}