package com.satydroid.saty_7eleven.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.satydroid.saty_7eleven.Logger
import com.satydroid.saty_7eleven.R

class GitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git)
        gitFirstCommit()
    }

    private fun gitFirstCommit() {
        Logger.s("gitFirstCommit")
    }
}