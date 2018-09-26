package com.example.arkadiuszkostka.liveread.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.arkadiuszkostka.liveread.Db.HealthEntry
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.InjectionUtil

class HealthViewModel(application: Application) : AndroidViewModel(application) {

    private var healthData: LiveData<List<HealthEntry>>

    init {
        logInfo("start taking EntertainmentData from repository -> Database", this)

        healthData = InjectionUtil.provideRepository(application.applicationContext).getHealthDataToViewModel()
    }

    fun getHealthData(): LiveData<List<HealthEntry>> = healthData
}