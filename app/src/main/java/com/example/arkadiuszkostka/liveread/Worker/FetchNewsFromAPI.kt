package com.example.arkadiuszkostka.liveread.Worker

import androidx.work.Worker
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.InjectionUtil
import com.example.arkadiuszkostka.liveread.Repository

class FetchNewsFromAPI: Worker() {
    override fun doWork(): Result {
        val country = inputData.getString(Repository.WORKER_VALUE_BUSINESS)
        val category = inputData.getString(Repository.TYPE_OF_ARTCLE)!!
        logInfo(category,this)
        InjectionUtil.provideNetworkProvider().getBusinessInformation(country!!,category)

        return Result.SUCCESS
    }

}