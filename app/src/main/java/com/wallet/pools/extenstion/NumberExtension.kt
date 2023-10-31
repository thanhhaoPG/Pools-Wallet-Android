package com.wallet.pools.extenstion

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun formatDoubleWithThreeDecimals(number: Double): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US) // Use the desired locale
    numberFormat.minimumFractionDigits = 0 // Set minimum fraction digits
    numberFormat.maximumFractionDigits = 3 // Set maximum fraction digits
    return numberFormat.format(number)
}

fun formatDoubleWithTwoDecimals(number: Double): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US) // Use the desired locale
    numberFormat.minimumFractionDigits = 0 // Set minimum fraction digits
    numberFormat.maximumFractionDigits = 2 // Set maximum fraction digits
    return numberFormat.format(number)
}


fun formatDoubleWithCommas(number: Double): String {
    val numberFormat = NumberFormat.getInstance() // Use the default locale
    return numberFormat.format(number)
}