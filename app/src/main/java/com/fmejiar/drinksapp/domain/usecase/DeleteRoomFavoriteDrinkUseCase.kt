package com.fmejiar.drinksapp.domain.usecase

import com.fmejiar.drinksapp.domain.repository.DrinkRepository
import com.fmejiar.model.home.drink.Drink

// import com.fmejiar.drinksapp.domain.response.drinks.Drink

class DeleteRoomFavoriteDrinkUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drink: Drink) = drinkRepository.deleteRoomFavoriteDrink(drink)
}