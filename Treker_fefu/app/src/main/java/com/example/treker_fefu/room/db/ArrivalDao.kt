package com.example.treker_fefu.room.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArrivalDao {
    @Query("SELECT * FROM arrival_db ORDER BY time_finish  DESC ")
    fun getAll(): LiveData<List<ArrivalRoom>>

    @Query("SELECT * FROM arrival_db WHERE id=:id")
    fun getById(id: Int): ArrivalRoom

    @Insert
    fun insert(activity : ArrivalRoom)

    @Delete
    fun delete(activity: ArrivalRoom)
}