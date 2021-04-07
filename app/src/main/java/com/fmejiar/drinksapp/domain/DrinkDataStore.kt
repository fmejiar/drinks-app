package com.fmejiar.drinksapp.domain

import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.drinksapp.vo.RetrofitApiClient

interface DrinkDataStore {

    suspend fun getDrinkByName(drinkName: String): ResultType<List<Drink>>
    suspend fun insertRoomDrink(drinkEntity: DrinkEntity)
    suspend fun getRoomFavoriteDrinksList(): ResultType<List<DrinkEntity>>

}