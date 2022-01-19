package com.example.treker_fefu.room.math


import android.text.format.DateUtils
import com.google.android.gms.common.util.DataUtils
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun LocalDateTime.toDateSeparator(): String {

    val date_time_ms = System.currentTimeMillis()
    val months = mapOf(
        1 to "Январь",
        2 to "Февраль",
        3 to "Март",
        4 to "Апрель",
        5 to "Май",
        6 to "Июнь",
        7 to "Июль",
        8 to "Август",
        9 to "Сентябрь",
        10 to "Октябрь",
        11 to "Ноябрь",
        12 to "Декабрь",
    )

    return when {
        DateUtils.isToday(date_time_ms) -> "Сегодня"
        DateUtils.isToday(date_time_ms.plus(DateUtils.DAY_IN_MILLIS)) -> "Вчера"
        DateUtils.isToday(date_time_ms.plus(DateUtils.WEEK_IN_MILLIS)) -> "Неделю назад"
        DateUtils.isToday(date_time_ms.plus(DateUtils.WEEK_IN_MILLIS * 4)) -> "Месяц назад"
        else -> "${months[monthValue]} $year года"
    }
}

fun LocalDateTime.toFinishDateOrTime(): String {
    val curDateTime = LocalDateTime.now()
    val duration = Duration.between(this, curDateTime)

    return when {
        -duration.toHours() < 24 -> {
            var time: String = "f"
            time = if (duration.toHours().toString() != "0") {
                "${-duration.toHours()} ч. назад"
            } else {
                "${duration.toMinutes()} мин. назад"
            }
            return time
        }
        else -> this.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }
}

fun LocalDateTime.toTime(): String {
    return this.format(DateTimeFormatter.ofPattern("HH:mm"))
}

fun Duration.toFormattedDurationBetween(): String {
    return "${toHours()} ч. ${toMinutes() % 60} м."
}
