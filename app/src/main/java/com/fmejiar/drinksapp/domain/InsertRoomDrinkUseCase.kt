package com.fmejiar.drinksapp.domain

import com.fmejiar.drinksapp.data.model.DrinkEntity

class InsertRoomDrinkUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drinkEntity: DrinkEntity) = drinkRepository.insertRoomDrink(drinkEntity)
}