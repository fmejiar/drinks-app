package com.fmejiar.drinksapp.domain

import com.fmejiar.drinksapp.data.model.DrinkEntity

class GetRoomFavoriteDrinksListUseCase(private val drinkRepository: DrinkRepository) {

    operator fun invoke() = drinkRepository.getRoomFavoriteDrinksList()
}