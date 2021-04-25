package com.fmejiar.drinksapp.domain.repository

import androidx.lifecycle.LiveData
import com.fmejiar.drinksapp.data.local.DrinkEntity
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.model.home.drinks.Drink

interface DrinkRepository {

    suspend fun getDrinksList(drinkName: String): ResultType<List<Drink>>
    fun getRoomFavoriteDrinksList(): LiveData<List<Drink>>
    suspend fun insertRoomDrink(drinkEntity: DrinkEntity)
    suspend fun deleteRoomFavoriteDrink(drink: Drink)
    suspend fun isDrinkFavorite(drinkId: String): Boolean
    suspend fun fetchDrinkById(drinkId: String): ResultType<List<Drink>>
}