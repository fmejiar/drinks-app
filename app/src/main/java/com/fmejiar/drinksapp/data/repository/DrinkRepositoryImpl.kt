package com.fmejiar.drinksapp.data.repository

import androidx.lifecycle.LiveData
import com.fmejiar.drinksapp.data.local.DrinkEntity
import com.fmejiar.drinksapp.data.datastore.DrinkDataStore
import com.fmejiar.drinksapp.domain.repository.DrinkRepository
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.model.home.drinks.Drink

class DrinkRepositoryImpl(private val drinkDataStore: DrinkDataStore) : DrinkRepository {

    override suspend fun getDrinksList(drinkName: String): ResultType<List<Drink>> =
            drinkDataStore.getDrinkByName(drinkName)

    override fun getRoomFavoriteDrinksList(): LiveData<List<Drink>> =
            drinkDataStore.getRoomFavoriteDrinksList()

    override suspend fun insertRoomDrink(drinkEntity: DrinkEntity) =
            drinkDataStore.insertRoomDrink(drinkEntity)

    override suspend fun deleteRoomFavoriteDrink(drink: Drink) =
            drinkDataStore.deleteRoomFavoriteDrink(drink)

    override suspend fun isDrinkFavorite(drinkId: String): Boolean =
            drinkDataStore.isDrinkFavorite(drinkId)

    override suspend fun fetchDrinkById(drinkId: String): ResultType<List<Drink>> =
            drinkDataStore.retrieveDrinkById(drinkId)

}