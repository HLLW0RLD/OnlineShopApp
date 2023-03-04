package com.example.app.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.app.base.BaseApp

object Helper {

    fun toastShort(text: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(BaseApp.appInstance, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun toastLong(text: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(BaseApp.appInstance, text, Toast.LENGTH_LONG).show()
        }

    }
}