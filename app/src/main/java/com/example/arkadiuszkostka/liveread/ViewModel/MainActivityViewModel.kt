package com.example.arkadiuszkostka.liveread.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.InjectionUtil

class MainActivityViewModel(aplication: Application): AndroidViewModel(aplication) {


    init {
        logInfo("MainActivityViewModel start initialize fetch data",this)
        InjectionUtil.provideRepository(aplication.applicationContext).chooseRightDataSource()
    }
}