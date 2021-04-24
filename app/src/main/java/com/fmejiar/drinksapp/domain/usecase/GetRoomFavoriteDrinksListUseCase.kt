package com.fmejiar.drinksapp.domain.usecase

import com.fmejiar.drinksapp.domain.repository.DrinkRepository

class GetRoomFavoriteDrinksListUseCase(private val drinkRepository: DrinkRepository) {

    operator fun invoke() = drinkRepository.getRoomFavoriteDrinksList()
}