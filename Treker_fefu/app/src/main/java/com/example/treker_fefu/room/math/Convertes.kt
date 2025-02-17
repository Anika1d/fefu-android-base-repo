package com.example.treker_fefu.room.math

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

class Converters {
    val instant: Instant = Instant.now()
    val systemZone: ZoneId = ZoneId.systemDefault()
    val currentOffsetForMyZone: ZoneOffset = systemZone.rules.getOffset(instant)
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): Long? {
        return value?.toEpochSecond(currentOffsetForMyZone)
    }

    @TypeConverter
    fun toLocalDateTime(value: Long?): LocalDateTime? {
        return value?.let { LocalDateTime.ofEpochSecond(it, 0, currentOffsetForMyZone) }
    }

    @TypeConverter
    fun fromCoordinates(value: List<Pair<Double, Double>>?): String? {
        return value?.let {
            val gsonCoordinatesBuilder = GsonBuilder().create()
            val type = object: TypeToken<List<Pair<Double, Double>>>() {}.type
            gsonCoordinatesBuilder.toJson(value, type)
        }

    }

    @TypeConverter
    fun toCoordinates(value: String?): List<Pair<Double, Double>>? {
        return value?.let {
            val gsonCoordinatesBuilder = GsonBuilder().create()
            val type = object: TypeToken<List<Pair<Double, Double>>>() {}.type
            gsonCoordinatesBuilder.fromJson(it, type)
        }
    }
}