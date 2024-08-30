package com.irvin.cryptocurrency.presentation.ui

import com.irvin.cryptocurrency.domain.entities.Currency
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun formattingDecimalFormatCryptocurrencyName(
    price: Double,
    symbols: DecimalFormatSymbols
): DecimalFormat {
    return if ((price / 0.0001).toInt() == 0) {
        DecimalFormat("#,##0.000000", symbols)
    } else if ((price / 0.001).toInt() == 0) {
        DecimalFormat("#,##0.00000", symbols)
    } else if ((price / 0.01).toInt() == 0) {
        DecimalFormat("#,##0.0000", symbols)
    } else {
        DecimalFormat("#,##0.00", symbols)
    }
}

fun formattingCryptoPrice(
    pickedCurrency: Currency, price: Double,
    symbols: DecimalFormatSymbols
): String {
    val decimalFormatCryptocurrencyName = formattingDecimalFormatCryptocurrencyName(price, symbols)
    return (if (pickedCurrency == Currency.USD) "$ " else "₽ ") + decimalFormatCryptocurrencyName.format(
        price
    )
}