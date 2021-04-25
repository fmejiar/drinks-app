package com.fmejiar.drinksapp.data.datastore

import androidx.lifecycle.LiveData
import com.fmejiar.drinksapp.data.model.DrinkEntity
// import com.fmejiar.drinksapp.domain.response.drinks.Drink
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.drinksapp.vo.RetrofitApiClient
import com.fmejiar.model.home.drink.Drink

interface DrinkDataStore {

    suspend fun getDrinkByName(drinkName: String): ResultType<List<Drink>>
    suspend fun insertRoomDrink(drinkEntity: DrinkEntity)
    fun getRoomFavoriteDrinksList(): LiveData<List<Drink>>
    suspend fun deleteRoomFavoriteDrink(drink: Drink)
    suspend fun isDrinkFavorite(drinkId: String): Boolean
    suspend fun retrieveDrinkById(drinkId: String): ResultType<List<Drink>>

}