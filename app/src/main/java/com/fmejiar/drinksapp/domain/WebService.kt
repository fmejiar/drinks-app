package com.fmejiar.drinksapp.domain

import com.fmejiar.drinksapp.data.model.DrinksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getDrinksByName(@Query(value = "s") drinkName: String): DrinksResponse

}