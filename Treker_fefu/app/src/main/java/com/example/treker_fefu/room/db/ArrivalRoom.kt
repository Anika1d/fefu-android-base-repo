package com.example.treker_fefu.room.db

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.treker_fefu.model.arrival.ListArrival
import com.example.treker_fefu.model.map.MapItemType
import com.example.treker_fefu.room.math.*
import java.text.SimpleDateFormat
import java.time.*

@Entity(tableName = "arrival_db")
data class ArrivalRoom(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name_arrival") val name_arrival: Int,
    @ColumnInfo(name = "nickname") val nickname: String,
    @ColumnInfo(name = "time_start") val time_start: LocalDateTime,
    @ColumnInfo(name = "time_finish") val time_finish: LocalDateTime,
    @ColumnInfo(name = "coords") val coords: List<Pair<Double, Double>>,
) {
    @SuppressLint("SimpleDateFormat")
    fun toArrival(): ListArrival.Arrival {
        return ListArrival.Arrival(
            id = id,
            name_arrival = MapItemType.values()[name_arrival].type,
            distance = coords.getDistance().toFormatDistance(),
            time = Duration.between(time_start, time_finish).toFormattedDurationBetween(),
            date = time_finish.toFinishDateOrTime(),
            time_start = time_start.toTime(),
            time_finish = time_finish.toTime(),
            nickname = "anika"
        )
    }
}