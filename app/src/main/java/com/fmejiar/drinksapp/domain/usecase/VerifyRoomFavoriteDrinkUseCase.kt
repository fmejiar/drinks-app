package com.fmejiar.drinksapp.domain.usecase

import com.fmejiar.drinksapp.domain.repository.DrinkRepository

class VerifyRoomFavoriteDrinkUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drinkId: String) = drinkRepository.isDrinkFavorite(drinkId)
}