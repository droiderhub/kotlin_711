package com.satydroid.saty_7eleven.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SatyViewModel : ViewModel() {

    lateinit var sum : MutableLiveData<Boolean>

    init {
        sum = MutableLiveData()
    }

    fun doSummationViewModel(a:Int, b:Int) :Int{
        sum.value=(false)
        var sumValue :Int =0
        SatyRepo.doSummation(a,b) { a, b ->
            sumValue = a + b

        }
        return sumValue
    }


    fun getSummationObservable() : LiveData<Boolean>{
        return sum
    }
}