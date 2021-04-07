package com.fmejiar.drinksapp.domain

import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.vo.ResultType

class DrinkRepositoryImpl(private val drinkDataStore: DrinkDataStore) : DrinkRepository {

    override suspend fun getDrinksList(drinkName: String): ResultType<List<Drink>> =
        drinkDataStore.getDrinkByName(drinkName)

    override suspend fun getRoomFavoriteDrinksList(): ResultType<List<DrinkEntity>> {
        return drinkDataStore.getRoomFavoriteDrinksList()
    }

    override suspend fun insertRoomDrink(drinkEntity: DrinkEntity) {
        drinkDataStore.insertRoomDrink(drinkEntity)
    }

}