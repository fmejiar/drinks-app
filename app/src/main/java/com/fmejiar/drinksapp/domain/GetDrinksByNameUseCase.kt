package com.fmejiar.drinksapp.domain

class GetDrinksByNameUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drinkName: String) = drinkRepository.getDrinksList(drinkName)
}