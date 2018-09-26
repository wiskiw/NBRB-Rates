package com.nbrb_rates.wiskiw.nbrb_rates.model

data class CurrencyRate(val id: Int) {

    var numCode: Int = 0
    var charCode: Char? = null
    var scale: Int = 0
    var name: String = ""
    var rate: Double = 0.0

    /*
    <Currency Id="170">
        <NumCode>036</NumCode>
        <CharCode>AUD</CharCode>
        <Scale>1</Scale>
        <Name>Австралийский доллар</Name>
        <Rate>1.5208</Rate>
    </Currency>
     */
}