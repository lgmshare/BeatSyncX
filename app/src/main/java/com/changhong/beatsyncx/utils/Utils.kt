package com.changhong.beatsyncx.utils

import android.util.Log
import com.chad.library.BuildConfig
import java.text.SimpleDateFormat
import java.util.Locale

class Utils {

    companion object {

        fun log(msg: String?) {
            if (BuildConfig.DEBUG) {
                if (!msg.isNullOrEmpty()) {
                    Log.d("LogHelper", msg)
                }
            }
        }

        fun logE(msg: String?) {
            if (BuildConfig.DEBUG) {
                if (!msg.isNullOrEmpty()) {
                    Log.e("LogHelper", msg)
                }
            }
        }

        fun formatDatetime(datetime: Long): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            return simpleDateFormat.format(datetime)
        }
    }
}