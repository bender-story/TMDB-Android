package com.rahul.themoviedb.common.utils

fun Int.convertMinutesToHoursAndMinutes(): String {
    val hours = this / 60
    val remainingMinutes = this % 60
    return "${hours}h ${remainingMinutes}m"
}

fun Double.formatToOneDecimalPlaces(): String {
    return String.format("%.1f", this)
}