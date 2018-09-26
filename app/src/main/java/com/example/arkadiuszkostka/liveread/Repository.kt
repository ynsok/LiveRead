package com.example.arkadiuszkostka.liveread

import android.arch.lifecycle.LiveData
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.arkadiuszkostka.liveread.Db.*
import com.example.arkadiuszkostka.liveread.Extention.logInfo
import com.example.arkadiuszkostka.liveread.Network.InternetMag
import com.example.arkadiuszkostka.liveread.Network.Model
import com.example.arkadiuszkostka.liveread.Network.NetworkProvider
import com.example.arkadiuszkostka.liveread.Worker.AppExecutor
import com.example.arkadiuszkostka.liveread.Worker.FetchNewsFromAPI

class Repository private constructor(private val networkProvider: NetworkProvider,
                                     private val internetMag: InternetMag,
                                     private val executor: AppExecutor,
                                     private val newsDao: NewsDao) {


    private var mInitializer: Boolean = false


    init {
        val takeCurrentlyData = networkProvider.getBusinessDataFromAPI()
        takeCurrentlyData.observeForever({ t ->
            executor.diskIO().execute {
                logInfo("workForBusiness", this)
                newsDao.deleteAllBusinessArticles()
                val businessEntry: List<BusinessEntry> = createNewsEntryModel(t?.articles!!)
                newsDao.insertAllBusinessArticles(businessEntry)
            }
        })
    }

    init {

        val takeEntertimentData = networkProvider.getEntertimentDataFromAPI()
        takeEntertimentData.observeForever({ t ->
            executor.diskIO().execute {
                logInfo("workForEntertiment", this)
                newsDao.deleteAllEntertainmentArticles()
                val entertainmentEntry: List<EntertainmentEntry> = createEntertaimentEntryModel(t?.articles!!)
                newsDao.insertAllEntertainmentArticles(entertainmentEntry)
            }
        })
    }

    init {

        val takeHealthData = networkProvider.getHealthDataFromAPI()
        takeHealthData.observeForever({ t ->
            executor.diskIO().execute {
                logInfo("workForHealth", this)
                newsDao.deleteAllHealthArticles()
                val healthEntry: List<HealthEntry> = createHealthEntryModel(t?.articles!!)
                newsDao.insertAllHealthArticles(healthEntry)
            }
        })
    }

    init {

        val takeScienceData = networkProvider.getScienceDataFromAPI()
        takeScienceData.observeForever({ t ->
            executor.diskIO().execute {
                logInfo("workForScience", this)
                newsDao.deleteAllScienceArticles()
                val scienceEntry: List<ScienceEntry> = createScienceEntryModel(t?.articles!!)
                newsDao.insertAllScienceArticles(scienceEntry)
            }
        })
    }

    init {

        val takeSportsData = networkProvider.getSportsDataFromAPI()
        takeSportsData.observeForever({ t ->
            executor.diskIO().execute {
                logInfo("workForSports", this)
                newsDao.deleteAllSportsArticles()
                val sportsEntry: List<SportsEntry> = createSportsEntryModel(t?.articles!!)
                newsDao.insertAllSportsArticles(sportsEntry)
            }
        })
    }

    init {

        val takeTechnologyData = networkProvider.getTechnologyDataFromAPI()
        takeTechnologyData.observeForever({ t ->
            executor.diskIO().execute {
                logInfo("workForTechnology", this)
                newsDao.deleteAllTechnologyArticles()
                val technologyEntry: List<TechnologyEntry> = createTechnologyEntryModel(t?.articles!!)
                newsDao.insertAllTechnologyArticles(technologyEntry)
            }
        })
    }

    fun getBusinessDataInViewModel(): LiveData<List<BusinessEntry>> = newsDao.getAllBusinessNews()
    fun getBusinessDataById(id: Int): LiveData<BusinessEntry> = newsDao.getBusinessNewsById(id)

    fun getEntertaimentDataToViewModel(): LiveData<List<EntertainmentEntry>> = newsDao.getAllEntertainmentNews()
    fun getEntertainmentDataById(id: Int): LiveData<EntertainmentEntry> = newsDao.getEntertainmentNewsById(id)

    fun getHealthDataToViewModel(): LiveData<List<HealthEntry>> = newsDao.getAllHealthNews()
    fun getHealthDataById(id: Int): LiveData<HealthEntry> = newsDao.getHealthNewsById(id)

    fun getScienceDataToViewModel(): LiveData<List<ScienceEntry>> = newsDao.getAllScienceNews()
    fun getScienceDataById(id: Int): LiveData<ScienceEntry> = newsDao.getScienceNewsById(id)

    fun getSportsDataToViewModel(): LiveData<List<SportsEntry>> = newsDao.getAllSportsNews()
    fun getSportsDataById(id: Int): LiveData<SportsEntry> = newsDao.getSportsNewsById(id)

    fun getTechnologyDataToViewModel(): LiveData<List<TechnologyEntry>> = newsDao.getAllTechnologyNews()
    fun getTechnologyDataById(id: Int): LiveData<TechnologyEntry> = newsDao.getTechnologyNewsById(id)


    //    Method to decide which kind of Data source choose, or API or ROOM
    fun chooseRightDataSource() {

        logInfo("Check which use DataSource", this)
        if (internetMag.checkIfIsInternet()) {
            initializeData()

        }

    }

    @Synchronized
    private fun initializeData() {

        logInfo("InitializeData", this)

        if (mInitializer) return
        mInitializer = true
//TODO
        startFetchingDataThroughWorker()

    }

    private fun createDataBuilderForWorker(typeOfArticle: String): Data {

        return Data.Builder()
                .putString(WORKER_VALUE_BUSINESS, internetMag.getLoca())
                .putString(TYPE_OF_ARTCLE, typeOfArticle)
                .build()
    }

    private fun createWorkerData(typeOfArticle: String): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(FetchNewsFromAPI::class.java)
                .setInputData(createDataBuilderForWorker(typeOfArticle))
                .build()
    }


    private fun startFetchingDataThroughWorker() {
        WorkManager.getInstance().beginWith(createWorkerData(BUSINESS_TYPE))
                .then(createWorkerData(ENTERTAINMENT_TYPE))
                .then(createWorkerData(HEALTH_TYPE))
                .then(createWorkerData(SCIENCE_TYPE))
                .then(createWorkerData(SPORTS_TYPE))
                .then(createWorkerData(TECHNOLOGY_TYPE))
                .enqueue()
    }


    private fun createNewsEntryModel(articles: List<Model.Articles>): MutableList<BusinessEntry> {

        val listOfNewsEntry = mutableListOf<BusinessEntry>()
        articles.forEachIndexed { index, articles ->
            listOfNewsEntry.add(
                    BusinessEntry(
                            articles.author,
                            articles.title,
                            articles.description,
                            articles.url,
                            articles.urlToImage,
                            articles.publishedAt,
                            articles.source.id,
                            articles.source.name,
                            index
                    )
            )
        }

        return listOfNewsEntry

    }

    private fun createEntertaimentEntryModel(articles: List<Model.Articles>): MutableList<EntertainmentEntry> {
        val listOfNewsEntry = mutableListOf<EntertainmentEntry>()
        articles.forEachIndexed { index, articles ->
            listOfNewsEntry.add(
                    EntertainmentEntry(
                            articles.author,
                            articles.title,
                            articles.description,
                            articles.url,
                            articles.urlToImage,
                            articles.publishedAt,
                            articles.source.id,
                            articles.source.name,
                            index
                    )
            )
        }
        return listOfNewsEntry

    }

    private fun createHealthEntryModel(articles: List<Model.Articles>): MutableList<HealthEntry> {
        val listOfNewsEntry = mutableListOf<HealthEntry>()
        articles.forEachIndexed { index, articles ->
            listOfNewsEntry.add(
                    HealthEntry(
                            articles.author,
                            articles.title,
                            articles.description,
                            articles.url,
                            articles.urlToImage,
                            articles.publishedAt,
                            articles.source.id,
                            articles.source.name,
                            index
                    )
            )
        }
        return listOfNewsEntry

    }

    private fun createScienceEntryModel(articles: List<Model.Articles>): MutableList<ScienceEntry> {
        val listOfNewsEntry = mutableListOf<ScienceEntry>()
        articles.forEachIndexed { index, articles ->
            listOfNewsEntry.add(
                    ScienceEntry(
                            articles.author,
                            articles.title,
                            articles.description,
                            articles.url,
                            articles.urlToImage,
                            articles.publishedAt,
                            articles.source.id,
                            articles.source.name,
                            index
                    )
            )
        }
        return listOfNewsEntry

    }

    private fun createSportsEntryModel(articles: List<Model.Articles>): MutableList<SportsEntry> {


        val listOfNewsEntry = mutableListOf<SportsEntry>()
        articles.forEachIndexed { index, articles ->
            listOfNewsEntry.add(
                    SportsEntry(
                            articles.author,
                            articles.title,
                            articles.description,
                            articles.url,
                            articles.urlToImage,
                            articles.publishedAt,
                            articles.source.id,
                            articles.source.name,
                            index
                    )
            )
        }
        return listOfNewsEntry

    }

    private fun createTechnologyEntryModel(articles: List<Model.Articles>): MutableList<TechnologyEntry> {

        val listOfNewsEntry = mutableListOf<TechnologyEntry>()

        articles.forEachIndexed { index, articles ->
            listOfNewsEntry.add(
                    TechnologyEntry(
                            articles.author,
                            articles.title,
                            articles.description,
                            articles.url,
                            articles.urlToImage,
                            articles.publishedAt,
                            articles.source.id,
                            articles.source.name,
                            index
                    )
            )
            logInfo(index.toString(), this)
        }
        return listOfNewsEntry

    }

    companion object {


//    Volaritle - Marks the JVM backing field of the annotated property as volatile,
// meaning that writes to this field are immediately made visible to other threads.
//    It's mean that the Instance will be just One TIme Created

        const val WORKER_VALUE_BUSINESS = "downloadBusiness"
        const val TYPE_OF_ARTCLE = "typeOfArticle"
        const val BUSINESS_TYPE = "business"
        const val ENTERTAINMENT_TYPE = "entertainment"
        const val HEALTH_TYPE = "health"
        const val SCIENCE_TYPE = "science"
        const val SPORTS_TYPE = "sports"
        const val TECHNOLOGY_TYPE = "technology"
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(networkProvider: NetworkProvider, internetMag: InternetMag, executor: AppExecutor, newsDao: NewsDao): Repository =
                INSTANCE ?: synchronized(this)
                {
                    INSTANCE
                            ?: Repository(networkProvider, internetMag, executor, newsDao)
                }
                        .also {
                            INSTANCE = it
                            logInfo("Create New Instance", this)
                        }
    }
}