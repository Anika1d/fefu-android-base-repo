package com.example.treker_fefu.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.treker_fefu.room.math.Converters


@Database(entities = [ArrivalRoom::class], version = 2)
@TypeConverters(Converters::class)
abstract class ArrivalDatabase: RoomDatabase() {
    abstract fun myActivityDao(): ArrivalDao
}