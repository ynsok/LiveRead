package com.example.arkadiuszkostka.liveread.Network

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.example.arkadiuszkostka.liveread.Extention.logInfo

class InternetMag(val context: Context) {
    fun checkIfIsInternet(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        logInfo("CheckTheInternet",this)
        return networkInfo != null && networkInfo.isConnected
    }

        fun getLoca(): String {
            return when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> context.resources.configuration.locales.get(0).country

                else -> context.resources.configuration.locale.country
            }
        }
    }
