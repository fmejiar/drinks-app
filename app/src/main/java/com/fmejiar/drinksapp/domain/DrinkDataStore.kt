package com.fmejiar.drinksapp.domain

import androidx.lifecycle.LiveData
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.drinksapp.vo.RetrofitApiClient

interface DrinkDataStore {

    suspend fun getDrinkByName(drinkName: String): ResultType<List<Drink>>
    suspend fun insertRoomDrink(drinkEntity: DrinkEntity)
    fun getRoomFavoriteDrinksList(): LiveData<List<Drink>>
    suspend fun deleteRoomFavoriteDrink(drink: Drink)
    suspend fun isDrinkFavorite(drink: Drink): Boolean
    suspend fun retrieveDrinkById(drinkId: String): ResultType<List<Drink>>

}