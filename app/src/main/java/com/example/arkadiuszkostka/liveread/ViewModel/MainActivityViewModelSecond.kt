package com.example.arkadiuszkostka.liveread.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.arkadiuszkostka.liveread.Db.KeywordEntry
import com.example.arkadiuszkostka.liveread.InjectionUtil
import com.example.arkadiuszkostka.liveread.Repository

class MainActivityViewModelSecond(context: Application) : AndroidViewModel(context) {

    private val repository: Repository = InjectionUtil.provideRepository(context.applicationContext)
    private val getRespondDataByKeyword: LiveData<List<KeywordEntry>>

    init {
        getRespondDataByKeyword = repository.getKeywordInViewModel()
    }

    fun getDataByKeyword(): LiveData<List<KeywordEntry>> = getRespondDataByKeyword



}