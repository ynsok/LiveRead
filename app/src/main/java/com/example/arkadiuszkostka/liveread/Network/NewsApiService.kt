package com.example.arkadiuszkostka.liveread.Network

import com.example.arkadiuszkostka.liveread.Extention.create
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiService {

    @GET("{type}")

    fun getArticlerListByTwoQuery(
                                  @Path("type") type: String,
                                  @Query("country") country: String,
                                  @Query("category") category: String,
                                  @Query("q") q: String,
                                  @Query("source") source: String,
                                  @Query("pageSize") pageSize: String,
                                  @Header("authorization") authorization: String
                                  ): Call<Model.Result>

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
         fun buildNetworkProvider(): NewsApiService {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create()

        }
    }
}