package com.fmejiar.drinksapp.domain

import androidx.lifecycle.LiveData
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.vo.ResultType

interface DrinkRepository {

    suspend fun getDrinksList(drinkName: String): ResultType<List<Drink>>
    fun getRoomFavoriteDrinksList(): LiveData<List<Drink>>
    suspend fun insertRoomDrink(drinkEntity: DrinkEntity)
    suspend fun deleteRoomFavoriteDrink(drink: Drink)

}