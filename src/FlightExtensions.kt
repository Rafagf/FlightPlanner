package com.rafag.flightplanner

import com.rafag.flightplanner.model.Price
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Date.getDateFormatted(): String {
    val format = SimpleDateFormat("dd/MM/yy")
    return format.format(this)
}

fun Date.getTimeFormatted(): String {
    val format = SimpleDateFormat("HH:mm")
    return format.format(this)
}

fun Price.getPriceFormatted(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.UK)
    numberFormat.minimumFractionDigits = 1
    numberFormat.maximumFractionDigits = 2
    return numberFormat.format(this.toDouble())
}

fun String.getDate(): Date {
    //TODO implement
    return Calendar.getInstance().time
}
