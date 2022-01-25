package com.example.treker_fefu

import android.app.Application
import androidx.room.Room
import com.example.treker_fefu.room.db.ArrivalDatabase


class App : Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    val db by lazy {
        Room.databaseBuilder(
            this,
            ArrivalDatabase::class.java,
            "arrival_db"
        ).allowMainThreadQueries().build()
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
    }

}