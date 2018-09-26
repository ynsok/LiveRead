package com.example.arkadiuszkostka.liveread.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.arkadiuszkostka.liveread.Db.EntertainmentEntry
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.InjectionUtil

class EntertainmentViewModel(application: Application) : AndroidViewModel(application) {

    private var entertainmentData: LiveData<List<EntertainmentEntry>>

    init {
        logInfo("start taking EntertainmentData from repository -> Database", this)

        entertainmentData = InjectionUtil.provideRepository(application.applicationContext).getEntertaimentDataToViewModel()
    }

    fun getEnterainmentData():LiveData<List<EntertainmentEntry>> = entertainmentData
}