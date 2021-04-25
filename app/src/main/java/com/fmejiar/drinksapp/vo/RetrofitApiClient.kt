package com.fmejiar.drinksapp.vo

import com.fmejiar.drinksapp.data.remote.WebService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiClient {

    private const val API_BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    private val httpClient by lazy {
        OkHttpClient.Builder().addInterceptor(interceptor())
    }

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build().create(WebService::class.java)
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}