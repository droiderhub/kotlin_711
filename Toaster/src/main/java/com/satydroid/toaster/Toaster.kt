package com.satydroid.toaster

import android.content.Context
import android.widget.Toast

class Toaster {
    companion object {
        fun makeToast(message: String, context: Context) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

}