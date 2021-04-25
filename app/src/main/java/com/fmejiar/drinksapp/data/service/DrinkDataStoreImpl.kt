package com.fmejiar.drinksapp.data.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.fmejiar.drinksapp.data.local.AppDatabase
import com.fmejiar.drinksapp.data.local.DrinkEntity
import com.fmejiar.drinksapp.data.datastore.DrinkDataStore
import com.fmejiar.drinksapp.data.mapper.toDomain
import com.fmejiar.drinksapp.utils.asDrinkEntity
import com.fmejiar.drinksapp.utils.asDrinksList
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.drinksapp.vo.RetrofitApiClient
import com.fmejiar.model.home.drinks.Drink
import java.lang.Exception

class DrinkDataStoreImpl(private val appDatabase: AppDatabase) : DrinkDataStore {

    /*override suspend fun getDrinkByName(drinkName: String): ResultType<List<Drink>> =
            ResultType.Success(RetrofitApiClient.webservice.getDrinksByName(drinkName)?.drinksList
                    ?: listOf())*/

    override suspend fun getDrinkByName(drinkName: String): ResultType<List<Drink>> {
        try {
            val response = RetrofitApiClient.webservice.getDrinksByName(drinkName)
            val model = response?.toDomain()
            model.let {
                return ResultType.Success(model?.drinksList ?: listOf())
            }
        } catch (e: Exception) {
            return ResultType.Failure(e)
        }
    }

    override suspend fun insertRoomDrink(drinkEntity: DrinkEntity) {
        appDatabase.drinkDao().insertFavoriteDrink(drinkEntity)
    }

    override fun getRoomFavoriteDrinksList(): LiveData<List<Drink>> =
            appDatabase.drinkDao().getAllFavoriteDrinksListWithChanges().map { it.asDrinksList() }

    override suspend fun deleteRoomFavoriteDrink(drink: Drink) {
        appDatabase.drinkDao().deleteFavoriteDrink(drink.asDrinkEntity())
    }

    override suspend fun isDrinkFavorite(drinkId: String): Boolean =
            appDatabase.drinkDao().getDrinkById(drinkId) != null

    override suspend fun retrieveDrinkById(drinkId: String): ResultType<List<Drink>> {
        try {
            val response = RetrofitApiClient.webservice.getDrinkById(drinkId)?.toDomain()?.drinksList
                    ?: listOf()
            response.let {
                return ResultType.Success(response)
            }
        } catch (e: Exception) {
            return ResultType.Failure(e)
        }
    }

}