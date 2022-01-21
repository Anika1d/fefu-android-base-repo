package com.example.treker_fefu.room.math


import java.time.*
import java.time.format.DateTimeFormatter

fun LocalDateTime.toDateSeparator(): String {
    val curDateTime = LocalDateTime.now()
    val duration= Duration.between(this,curDateTime)
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
         duration.toHours()<24 -> "Сегодня"
        duration.toHours() in 24..47 -> "Вчера"
        duration.toHours() in 48..167 -> "Неделю назад"
        duration.toHours() in 168..671 -> "Месяц назад"
        else -> "${months[monthValue]} $year года"
    }
}



fun LocalDateTime.toFinishDateOrTime(): String {
    val curDateTime = LocalDateTime.now()
    val duration= Duration.between(this,curDateTime)

    return when {
        -duration.toHours() <24 -> {
            val time = if (duration.toHours().toString() != "0") {
                "${duration.toHours()} ч. назад"
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
