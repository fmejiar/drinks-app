package com.fmejiar.drinksapp.domain

import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity

class VerifyRoomFavoriteDrinkUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drinkId: String) = drinkRepository.isDrinkFavorite(drinkId)
}