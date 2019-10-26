package com.rafag.flightplanner

import com.rafag.flightplanner.model.domain.Price
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_TIME_PATTERN = "yyyy-MM-dd'T'hh:mm"
private const val DATE_PATTERN = "dd/MM/yy"
private const val TIME_PATTERN = "HH:mm"

fun Date.getDateFormatted(): String {
    val format = SimpleDateFormat(DATE_PATTERN)
    return format.format(this)
}

fun Date.getTimeFormatted(): String {
    val format = SimpleDateFormat(TIME_PATTERN)
    return format.format(this)
}

fun Price.getPriceFormatted(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.UK)
    numberFormat.minimumFractionDigits = 1
    numberFormat.maximumFractionDigits = 2
    return numberFormat.format(this.toDouble())
}

fun String.getDate(): Date {
    val format = SimpleDateFormat(DATE_TIME_PATTERN)
    return format.parse(this)
}
