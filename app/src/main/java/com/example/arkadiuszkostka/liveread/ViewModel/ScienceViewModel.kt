package com.example.arkadiuszkostka.liveread.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.arkadiuszkostka.liveread.Db.ScienceEntry
import com.example.arkadiuszkostka.liveread.InjectionUtil

class ScienceViewModel(application: Application): AndroidViewModel(application) {


    private var dataFromD: LiveData<List<ScienceEntry>> = InjectionUtil.provideRepository(application.applicationContext).getScienceDataToViewModel()

    fun getDataFromScienceDB():LiveData<List<ScienceEntry>> = dataFromD

}