package com.fmejiar.drinksapp.domain

import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.vo.ResultType

interface DrinkRepository {

    suspend fun getDrinksList(drinkName: String): ResultType<List<Drink>>
    suspend fun getRoomFavoriteDrinksList(): ResultType<List<DrinkEntity>>
    suspend fun insertRoomDrink(drinkEntity: DrinkEntity)

}