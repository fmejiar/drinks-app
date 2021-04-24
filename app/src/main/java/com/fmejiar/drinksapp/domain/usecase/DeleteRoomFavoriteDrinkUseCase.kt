package com.fmejiar.drinksapp.domain.usecase

import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.domain.repository.DrinkRepository

class DeleteRoomFavoriteDrinkUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drink: Drink) = drinkRepository.deleteRoomFavoriteDrink(drink)
}