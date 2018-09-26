package com.example.arkadiuszkostka.liveread.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.arkadiuszkostka.liveread.Db.TechnologyEntry
import com.example.arkadiuszkostka.liveread.InjectionUtil

class TechnologyViewModel(application: Application): AndroidViewModel(application) {


    private var dataFromDB: LiveData<List<TechnologyEntry>> = InjectionUtil.provideRepository(application.applicationContext).getTechnologyDataToViewModel()

    fun getTechnologyData():LiveData<List<TechnologyEntry>> = dataFromDB


}