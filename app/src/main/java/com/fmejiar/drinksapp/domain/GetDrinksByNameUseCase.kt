package com.fmejiar.drinksapp.domain

import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.Ingredient
import com.fmejiar.drinksapp.vo.ResultType

class GetDrinksByNameUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drinkName: String): ResultType<List<Drink>> {

        return when (val result =
                drinkRepository.getDrinksList(drinkName)) {
            is ResultType.Loading -> {
                ResultType.Loading
            }
            is ResultType.Success -> {

                val drinksList = result.data

                drinksList.map { drink ->
                    drink.ingredients = getIngredientsListByDrink(drink)
                }

                ResultType.Success(drinksList)
            }
            is ResultType.Failure -> {
                ResultType.Failure(result.exception)
            }
        }
    }

    private fun getIngredientsListByDrink(drink: Drink): MutableList<Ingredient> {

        var ingredientsList: MutableList<Ingredient> = mutableListOf()

        ingredientsList = drink.ingredients
        if (drink.ingredient1 != null && drink.measure1 != null) {
            ingredientsList.add(Ingredient(drink.ingredient1, drink.measure1))
        }
        if (drink.ingredient2 != null && drink.measure2 != null) {
            ingredientsList.add(Ingredient(drink.ingredient2, drink.measure2))
        }
        if (drink.ingredient3 != null && drink.measure3 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient4 != null && drink.measure4 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient5 != null && drink.measure5 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient6 != null && drink.measure6 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient7 != null && drink.measure7 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient8 != null && drink.measure8 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient9 != null && drink.measure9 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient10 != null && drink.measure10 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient11 != null && drink.measure11 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient12 != null && drink.measure12 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient13 != null && drink.measure13 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient14 != null && drink.measure14 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (drink.ingredient15 != null && drink.measure15 != null) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }

        return ingredientsList
    }
}