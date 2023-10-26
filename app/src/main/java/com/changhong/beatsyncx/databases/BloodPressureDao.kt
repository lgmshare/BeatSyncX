package com.changhong.beatsyncx.databases

import androidx.room.*
import com.changhong.beatsyncx.models.Heartbeat

@Dao
interface BloodPressureDao {

    @Insert
    suspend fun insert(list: List<Heartbeat>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Heartbeat: Heartbeat): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(Heartbeat: Heartbeat): Int

    @Delete
    suspend fun delete(Heartbeat: Heartbeat)

    @Query("DELETE FROM ${AppDatabase.BP_TABLE} where id=:id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM ${AppDatabase.BP_TABLE}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${AppDatabase.BP_TABLE} where id=:id")
    fun queryById(id: Int): Heartbeat?

    @Query("SELECT * FROM ${AppDatabase.BP_TABLE}")
    suspend fun queryAll(): List<Heartbeat>

}