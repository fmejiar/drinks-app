package com.fmejiar.drinksapp.domain

import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.vo.ResultType

class GetDrinkByIdUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drinkId: String): ResultType<List<Drink>> = drinkRepository.fetchDrinkById(drinkId)

}