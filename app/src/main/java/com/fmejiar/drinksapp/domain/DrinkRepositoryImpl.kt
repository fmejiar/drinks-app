package com.fmejiar.drinksapp.domain

import androidx.lifecycle.LiveData
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.vo.ResultType

class DrinkRepositoryImpl(private val drinkDataStore: DrinkDataStore) : DrinkRepository {

    override suspend fun getDrinksList(drinkName: String): ResultType<List<Drink>> =
            drinkDataStore.getDrinkByName(drinkName)

    override fun getRoomFavoriteDrinksList(): LiveData<List<Drink>> =
            drinkDataStore.getRoomFavoriteDrinksList()

    override suspend fun insertRoomDrink(drinkEntity: DrinkEntity) =
            drinkDataStore.insertRoomDrink(drinkEntity)

    override suspend fun deleteRoomFavoriteDrink(drink: Drink) =
            drinkDataStore.deleteRoomFavoriteDrink(drink)

    override suspend fun isDrinkFavorite(drink: Drink): Boolean =
            drinkDataStore.isDrinkFavorite(drink)

    override suspend fun fetchDrinkById(drinkId: String): ResultType<List<Drink>> =
            drinkDataStore.retrieveDrinkById(drinkId)

}