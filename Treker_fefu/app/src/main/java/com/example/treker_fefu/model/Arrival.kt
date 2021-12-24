package com.example.treker_fefu.model

import java.time.LocalDate

data class Arrival(
    var id: Long,
    var name_arrival: String,
    val nick_user: String,
    var distance: Long, // в метрах
    var full_info_date:Triple<Int,Int,Int>, ////полная дата  //год месяц число
    var time_start: Triple<Int,Int,Int>,   /// сек мин час
    var time_finish: Triple<Int,Int,Int>,
    val comment:String
)
