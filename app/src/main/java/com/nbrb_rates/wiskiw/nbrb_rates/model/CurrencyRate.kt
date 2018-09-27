package com.nbrb_rates.wiskiw.nbrb_rates.model

data class CurrencyRate(val id: Int) {

    /*
    <Currency Id="170">
        <NumCode>036</NumCode>
        <CharCode>AUD</CharCode>
        <Scale>1</Scale>
        <Name>Австралийский доллар</Name>
        <Rate>1.5208</Rate>
    </Currency>
     */

    var numCode: Int = 0
    var charCode: String = ""
    var scale: Int = 0
    var name: String = ""
    var rate: Double = 0.0

    override fun toString(): String {
        return "CurrencyRate(id=$id, numCode=$numCode, charCode='$charCode', scale=$scale, name='$name', rate=$rate)"
    }
}