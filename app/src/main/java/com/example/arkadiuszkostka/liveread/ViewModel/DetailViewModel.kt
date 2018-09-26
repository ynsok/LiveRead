package com.example.arkadiuszkostka.liveread.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.arkadiuszkostka.liveread.Db.*
import com.example.arkadiuszkostka.liveread.Repository
import com.example.arkadiuszkostka.liveread.UI.Fragments.*

class DetailViewModel(repository: Repository, id: Int, source: String) : ViewModel() {


    var businessData: LiveData<BusinessEntry>? = null
    var entertainmentData: LiveData<EntertainmentEntry>? = null
    var healthData: LiveData<HealthEntry>? = null
    var scienceData: LiveData<ScienceEntry>? = null
    var sportsData: LiveData<SportsEntry>? = null
    var technologyData: LiveData<TechnologyEntry>? = null

    init {
        when (source) {
            Business.CATEGORY -> businessData = repository.getBusinessDataById(id)
            Entertainment.CATEGORY -> entertainmentData = repository.getEntertainmentDataById(id)
            Health.CATEGORY -> healthData = repository.getHealthDataById(id)
            Science.CATEGORY -> scienceData = repository.getScienceDataById(id)
            Sports.CATEGORY -> sportsData = repository.getSportsDataById(id)
            Technology.CATEGORY -> technologyData = repository.getTechnologyDataById(id)


        }


    }


}