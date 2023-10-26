package com.changhong.beatsyncx.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.changhong.beatsyncx.databases.AppDatabase
import kotlinx.android.parcel.Parcelize

@Entity(tableName = AppDatabase.BP_TABLE)
@Parcelize
data class Heartbeat(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var ave: Int = 0,
    var min: Int = 0,
    var max: Int = 0,
    var datetime: Long = 0,
) : Parcelable {

    fun level(): Int {
        var a: Boolean = false
        var b: Boolean = false
        if (max < 80 || min < 50 || ave < 60) {
            a = true
        }
        if (max > 200 || min > 120 || ave > 140) {
            b = true
        }
        if (!a && !b) {
            return 1
        }

        if (b && !a) {
            return 2
        }
        return 0
    }

}