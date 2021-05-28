package com.rishabh.testapp.blankFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.logging.Logger

class BlankFragmentViewModel : ViewModel() {

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    init {
        _counter.value = 0
    }

    fun plus() {
        _counter.value = (_counter.value ?: 0).plus(1)
        Logger.getGlobal().info("!!!!!!!!!!   plus  = ${_counter.value} ")
    }

}