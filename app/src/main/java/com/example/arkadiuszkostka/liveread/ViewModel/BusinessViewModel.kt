package com.example.arkadiuszkostka.liveread.ViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.arkadiuszkostka.liveread.Db.BusinessEntry
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.InjectionUtil

class BusinessViewModel(application: Application) : AndroidViewModel(application) {

    private var businnesBusinessArticle: LiveData<List<BusinessEntry>>


    init {
        logInfo("start taking BusinessData from repository -> Database", this)
        val newsRepository = InjectionUtil.provideRepository(application.applicationContext)
        businnesBusinessArticle = newsRepository.getBusinessDataInViewModel()
    }


    fun getNewsArticleFromWeb(): LiveData<List<BusinessEntry>> = businnesBusinessArticle
}