package com.fmejiar.drinksapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.fmejiar.drinksapp.AppDatabase
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.data.model.asDrinkEntity
import com.fmejiar.drinksapp.data.model.asDrinksList
import com.fmejiar.drinksapp.domain.DrinkDataStore
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.drinksapp.vo.RetrofitApiClient

class DrinkDataStoreImpl(private val appDatabase: AppDatabase) : DrinkDataStore {

    override suspend fun getDrinkByName(drinkName: String): ResultType<List<Drink>> =
        ResultType.Success(RetrofitApiClient.webservice.getDrinksByName(drinkName).drinksList)

    override suspend fun insertRoomDrink(drinkEntity: DrinkEntity) {
        appDatabase.drinkDao().insertFavoriteDrink(drinkEntity)
    }

    override fun getRoomFavoriteDrinksList(): LiveData<List<Drink>> =
        appDatabase.drinkDao().getAllFavoriteDrinksListWithChanges().map { it.asDrinksList() }

    override suspend fun deleteRoomFavoriteDrink(drink: Drink) {
        appDatabase.drinkDao().deleteFavoriteDrink(drink.asDrinkEntity())
    }
}