package com.fmejiar.drinksapp.data.remote

import com.fmejiar.drinksapp.domain.response.drinks.DrinksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getDrinksByName(@Query(value = "s") drinkName: String): DrinksResponse?

    @GET("lookup.php")
    suspend fun getDrinkById(@Query(value = "i") drinkId: String): DrinksResponse?
}