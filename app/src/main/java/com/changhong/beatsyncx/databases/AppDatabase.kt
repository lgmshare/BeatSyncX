package com.changhong.beatsyncx.databases

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.changhong.beatsyncx.App
import com.changhong.beatsyncx.models.Heartbeat

@Database(entities = [Heartbeat::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private const val DB = "bp.db"

        const val BP_TABLE = "bp_table"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(AppDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(App.getINS(), AppDatabase::class.java, DB)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                            }
                        })
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun dataDao(): BloodPressureDao

}
