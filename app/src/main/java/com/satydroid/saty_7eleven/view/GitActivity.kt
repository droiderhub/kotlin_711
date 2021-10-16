package com.satydroid.saty_7eleven.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.satydroid.saty_7eleven.Logger
import com.satydroid.saty_7eleven.R

class GitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git)
        changeInMasterBranch()
        changeInFeatureBranch()
        rebaseCodeInMaster()
        rebaseInFeature()
        rebaseCodeInMaster()

    }

    private fun rebaseCodeInMaster() {
        Logger.s("rebaseCodeInMaster")
    }

    private fun rebaseInFeature() {
        Logger.s("rebaseInFeature")
    }

    private fun rebaseCodeInMaster() {
        Logger.s("rebaseCodeInMaster")
    }

    private fun changeInFeatureBranch() {
        Logger.s("changeinFeatureBranch")
        gitFirstCommit()
        firstNewBranch()
        newFunInMasterBranch()
        mergerCommit01()
        mergerFromMasterBranch()

    }

    private fun changeInMasterBranch() {
        Logger.s("changeInMasterBranch")
    }

    private fun mergerCommit01() {
        Logger.s("mergerCommit01")
    }

    private fun mergerFromMasterBranch() {
        Logger.s("mergerFromMasterBranch")
    }

    private fun newFunInMasterBranch() {
        Logger.s("newFunInMasterBranch")
    }

    private fun firstNewBranch() {
        Logger.s("firstNewBranch")
    }

    private fun gitFirstCommit() {
        Logger.s("gitFirstCommit")
    }
}