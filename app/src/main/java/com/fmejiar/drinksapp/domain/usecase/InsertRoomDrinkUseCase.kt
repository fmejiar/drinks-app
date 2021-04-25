package com.fmejiar.drinksapp.domain.usecase

import com.fmejiar.drinksapp.data.local.DrinkEntity
import com.fmejiar.drinksapp.domain.repository.DrinkRepository

class InsertRoomDrinkUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drinkEntity: DrinkEntity) = drinkRepository.insertRoomDrink(drinkEntity)
}