package com.capiro.composables

import android.app.Activity
import android.app.Application
import android.content.Context

class BaseApplicationCapiroUI : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
    companion object {
        lateinit var activity: Activity
        lateinit var appContext: Context
    }
}