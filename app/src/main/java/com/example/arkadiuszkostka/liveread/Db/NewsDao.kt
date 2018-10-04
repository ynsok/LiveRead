package com.example.arkadiuszkostka.liveread.Db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface NewsDao {

    //    All for business Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBusinessArticles(listOfArticle: List<BusinessEntry>)

    @Query("Delete FROM news ")
    fun deleteAllBusinessArticles()

    @Query("Select * FROM news")
    fun getAllBusinessNews(): LiveData<List<BusinessEntry>>

    @Query("Select * FROM news WHERE id = :id")
    fun getBusinessNewsById(id: Int):LiveData<BusinessEntry>


    //    All for entertainment Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllEntertainmentArticles(listOfArticle: List<EntertainmentEntry>)

    @Query("Delete FROM entertainment ")
    fun deleteAllEntertainmentArticles()

    @Query("Select * FROM entertainment")
    fun getAllEntertainmentNews(): LiveData<List<EntertainmentEntry>>

    @Query("Select * FROM entertainment WHERE id = :id")
    fun getEntertainmentNewsById(id: Int):LiveData<EntertainmentEntry>


    //    All for health Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllHealthArticles(listOfArticle: List<HealthEntry>)

    @Query("Delete FROM health ")
    fun deleteAllHealthArticles()

    @Query("Select * FROM health")
    fun getAllHealthNews(): LiveData<List<HealthEntry>>

    @Query("Select * FROM health WHERE id = :id")
    fun getHealthNewsById(id: Int):LiveData<HealthEntry>


    //    All for Science Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllScienceArticles(listOfArticle: List<ScienceEntry>)

    @Query("Delete FROM science ")
    fun deleteAllScienceArticles()

    @Query("Select * FROM science")
    fun getAllScienceNews(): LiveData<List<ScienceEntry>>

    @Query("Select * FROM science WHERE id = :id")
    fun getScienceNewsById(id: Int):LiveData<ScienceEntry>


    //    All for Sports Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSportsArticles(listOfArticle: List<SportsEntry>)

    @Query("Delete FROM sports ")
    fun deleteAllSportsArticles()

    @Query("Select * FROM sports")
    fun getAllSportsNews(): LiveData<List<SportsEntry>>

    @Query("Select * FROM sports WHERE id = :id")
    fun getSportsNewsById(id: Int):LiveData<SportsEntry>


    //    All for Technology Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTechnologyArticles(listOfArticle: List<TechnologyEntry>)

    @Query("Delete FROM technology ")
    fun deleteAllTechnologyArticles()

    @Query("Select * FROM technology")
    fun getAllTechnologyNews(): LiveData<List<TechnologyEntry>>

    @Query("Select * FROM technology WHERE id = :id")
    fun getTechnologyNewsById(id: Int):LiveData<TechnologyEntry>


    //    All for Keyword Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllKeywordArticles(listOfArticle: List<KeywordEntry>)

    @Query("Delete FROM keyword ")
    fun deleteAllKeywordArticles()

    @Query("Select * FROM keyword")
    fun getAllKeywordNews(): LiveData<List<KeywordEntry>>

    @Query("Select * FROM keyword WHERE id = :id")
    fun getKeywordNewsById(id: Int):LiveData<KeywordEntry>







}