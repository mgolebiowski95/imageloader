package com.example.app

import android.app.Application
import com.examples.imageloaderlibrary.ImageLoader

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ImageLoader.getInstance().init(this)
    }
}