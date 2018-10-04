package com.example.arkadiuszkostka.liveread.Db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.arkadiuszkostka.liveread.Extention.logInfo

@Database(entities = [(BusinessEntry::class), (EntertainmentEntry::class), (HealthEntry::class), (ScienceEntry::class), (SportsEntry::class), (TechnologyEntry::class),(KeywordEntry::class)]
        , version = 1, exportSchema = false)
abstract class DataBaseCreator : RoomDatabase() {

    abstract fun newsDao(): NewsDao


    companion object {
        @Volatile
       private var INSTANCE:DataBaseCreator? = null

        fun createDB(context:Context):DataBaseCreator =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                        context.applicationContext,DataBaseCreator::class.java,"news_db"
                ).build().also { INSTANCE = it
                logInfo("Create New Instance Of DB",this)}
            }
        }
    }
