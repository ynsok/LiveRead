package com.example.arkadiuszkostka.liveread

import android.content.Context
import com.example.arkadiuszkostka.liveread.Db.DataBaseCreator
import com.example.arkadiuszkostka.liveread.Db.NewsDao
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.Network.InternetMag
import com.example.arkadiuszkostka.liveread.Network.NetworkProvider
import com.example.arkadiuszkostka.liveread.ViewModel.DetailViewModelFactory
import com.example.arkadiuszkostka.liveread.Worker.AppExecutor

object InjectionUtil {


    fun provideRepository(context: Context): Repository {
        logInfo("Get Repository", this)
        val dao: NewsDao = DataBaseCreator.createDB(context.applicationContext).newsDao()
        val executor = AppExecutor()
        val provider = provideNetworkProvider()
        val internetMag = provideInternetMag(context)
        return Repository.getInstance(provider, internetMag, executor, dao)
    }


    private fun provideInternetMag(context: Context): InternetMag {
        return InternetMag(context)
    }

    fun provideNetworkProvider(): NetworkProvider {

        logInfo("Get networkProvider", this)

        return NetworkProvider.getInstace()
    }
    fun provideDetailViewModelFactory(context:Context, id: Int, source: String):DetailViewModelFactory{
        val repository = provideRepository(context.applicationContext)
        return DetailViewModelFactory(mRepository = repository, id = id, source = source)
    }


}