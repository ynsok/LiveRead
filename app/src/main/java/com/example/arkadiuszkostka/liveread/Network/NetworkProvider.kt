package com.example.arkadiuszkostka.liveread.Network

import android.arch.lifecycle.MutableLiveData
import com.example.arkadiuszkostka.liveread.BuildConfig.KEY_API
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NetworkProvider private constructor(private val newsApiService: NewsApiService) {

    private val businessData: MutableLiveData<Model.Result> = MutableLiveData()
    private val entertimentData: MutableLiveData<Model.Result>
    private val healthData: MutableLiveData<Model.Result>
    private val scienceData: MutableLiveData<Model.Result>
    private val sportsData: MutableLiveData<Model.Result>
    private val technologyData: MutableLiveData<Model.Result>
    private val respondByKeyword: MutableLiveData<Model.Result>


    init {
        respondByKeyword = MutableLiveData()
        healthData = MutableLiveData()
        entertimentData = MutableLiveData()
        scienceData = MutableLiveData()
        sportsData = MutableLiveData()
        technologyData = MutableLiveData()

    }

    private fun searchNewsData(type: String, country: String, category: String, q: String, source: String, pageSize: String, autorization: String): Call<Model.Result> {
        logInfo("searchNewsData", this)

        return newsApiService.getArticlerListByTwoQuery(type, country, category, q, source, pageSize, autorization)

    }

    fun getQueryDataFromAPI(keyword: String) {
        searchNewsData("everything", "", "", keyword, "", "50", KEY_API).enqueue(object : Callback<Model.Result> {
            override fun onFailure(call: Call<Model.Result>?, t: Throwable?) {
                logInfo("Failure Everythin + ${t.toString()}", this)
            }

            override fun onResponse(call: Call<Model.Result>?, response: Response<Model.Result>?) {
                getRespondByKeywords().postValue(response?.body())
            }

        })

    }

    //Calling to take TopHeadlinesData from API
    fun getBusinessInformation(country: String, category: String) {
        searchNewsData("top-headlines", "US", category, "", "", "50", KEY_API).enqueue(object : Callback<Model.Result> {
            override fun onFailure(call: Call<Model.Result>?, t: Throwable?) {
                logInfo(t.toString(), this)
                logInfo(" $country", this)
            }


            override fun onResponse(call: Call<Model.Result>?, response: Response<Model.Result>?) {

                when (category) {
                    Repository.BUSINESS_TYPE -> getBusinessDataFromAPI().postValue(response?.body())

                    Repository.ENTERTAINMENT_TYPE -> getEntertimentDataFromAPI().postValue(response?.body())

                    Repository.HEALTH_TYPE -> getHealthDataFromAPI().postValue(response?.body())

                    Repository.SCIENCE_TYPE -> getScienceDataFromAPI().postValue(response?.body())

                    Repository.SPORTS_TYPE -> getSportsDataFromAPI().postValue(response?.body())

                    Repository.TECHNOLOGY_TYPE -> getTechnologyDataFromAPI().postValue(response?.body())
                }

                logInfo("Response Succese", this)
                logInfo(" $country and $category", this)

            }

        })
    }


    //    Send Data to Repository where with ObserveForever will be all the time catch
    fun getBusinessDataFromAPI(): MutableLiveData<Model.Result> {
        return businessData
    }

    fun getEntertimentDataFromAPI(): MutableLiveData<Model.Result> {
        return entertimentData
    }

    fun getHealthDataFromAPI(): MutableLiveData<Model.Result> {
        return healthData
    }

    fun getScienceDataFromAPI(): MutableLiveData<Model.Result> {
        return scienceData
    }

    fun getSportsDataFromAPI(): MutableLiveData<Model.Result> {
        return sportsData
    }

    fun getTechnologyDataFromAPI(): MutableLiveData<Model.Result> {
        return technologyData
    }

    fun getRespondByKeywords(): MutableLiveData<Model.Result> {
        return respondByKeyword
    }

    //    Provide static method if is networkConnection, and NetworkRepositori which is responsability of make call
    companion object {


        //        Create New instance Of NetworkProvider
        @Volatile
        private var INSTANCE: NetworkProvider? = null

        fun getInstace(): NetworkProvider =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: NetworkProvider(NewsApiService.buildNetworkProvider())
                                    .also {
                                        INSTANCE = it
                                        logInfo("Create New Instance", this)
                                    }

                }
    }
}