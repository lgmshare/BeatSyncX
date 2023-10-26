package com.changhong.beatsyncx

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class App : Application(), LifecycleEventObserver {
    companion object {

        private lateinit var instance: App

        fun getINS(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
            }

            Lifecycle.Event.ON_STOP -> {
            }

            else -> {
            }
        }
    }

}