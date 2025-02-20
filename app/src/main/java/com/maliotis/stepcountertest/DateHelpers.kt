package com.maliotis.stepcountertest

import android.app.AlarmManager
import android.content.Context
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Date.getYesterdaysDate(): Date {
    return Date(System.currentTimeMillis() - AlarmManager.INTERVAL_DAY)
}

fun Date.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    cal.time = this
    return cal
}

fun getDayDifference(todaysDate: Date, context: Context, minDiff: Long): Boolean {
    val dateYesterdayStepsString = getDayForYesterdaysSteps(context)
    if (dateYesterdayStepsString == "") {
        // this is the first time so return true
        return true
    }

    val oldDate = DateFormat.getDateInstance().parse(dateYesterdayStepsString)
    return if (oldDate != null) {
        val diff = todaysDate.time - oldDate.time
        val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
        days > minDiff
    } else {
        false
    }

}