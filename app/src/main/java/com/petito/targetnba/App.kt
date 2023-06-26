package com.petito.targetnba

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        fun getInstance(): Companion {
            return this
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}