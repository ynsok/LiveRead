package com.example.arkadiuszkostka.liveread.ViewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.arkadiuszkostka.liveread.Repository

class DetailViewModelFactory(private val mRepository: Repository, val id: Int, val source: String): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(mRepository,id,source) as T
    }
}