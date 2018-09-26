package com.example.arkadiuszkostka.liveread.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.arkadiuszkostka.liveread.Db.SportsEntry
import com.example.arkadiuszkostka.liveread.InjectionUtil

class SportsViewModel(application: Application):AndroidViewModel(application) {

    private var dataFromDB: LiveData<List<SportsEntry>> = InjectionUtil.provideRepository(application.applicationContext).getSportsDataToViewModel()

    fun getSportsDB(): LiveData<List<SportsEntry>> = dataFromDB



}