package com.example.arkadiuszkostka.liveread.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.InjectionUtil

class MainActivityViewModel(aplication: Application) : AndroidViewModel(aplication) {
    private val repository = InjectionUtil.provideRepository(aplication.applicationContext)

    init {
        logInfo("MainActivityViewModel start initialize fetch data", this)
        repository.chooseRightDataSource()
    }

    fun startFetchingDataByQuery(keyword: String) = InjectionUtil.provideNetworkProvider().getQueryDataFromAPI(keyword)
    fun delateAllKeywordData() = repository.deleteAllKeywordData()


}