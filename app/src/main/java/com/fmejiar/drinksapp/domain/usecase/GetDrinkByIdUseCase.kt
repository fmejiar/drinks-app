package com.fmejiar.drinksapp.domain.usecase

import com.fmejiar.drinksapp.domain.repository.DrinkRepository
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.model.home.drinks.Drink
import com.fmejiar.model.home.ingredients.Ingredient

class GetDrinkByIdUseCase(private val drinkRepository: DrinkRepository) {

    suspend operator fun invoke(drinkId: String): ResultType<List<Drink>> {
        return when (val result =
                drinkRepository.fetchDrinkById(drinkId)) {
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

    private fun getIngredientsListByDrink(drink: Drink): MutableList<Ingredient> =
            generateIngredientsList(drink, drink.ingredients)

    private fun generateIngredientsList(drink: Drink, ingredientsList: MutableList<Ingredient>): MutableList<Ingredient> {
        if (!drink.ingredient1.isNullOrEmpty() && !drink.measure1.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient1, drink.measure1))
        }
        if (!drink.ingredient2.isNullOrEmpty() && !drink.measure2.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient2, drink.measure2))
        }
        if (!drink.ingredient3.isNullOrEmpty() && !drink.measure3.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient3, drink.measure3))
        }
        if (!drink.ingredient4.isNullOrEmpty() && !drink.measure4.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient4, drink.measure4))
        }
        if (!drink.ingredient5.isNullOrEmpty() && !drink.measure5.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient5, drink.measure5))
        }
        if (!drink.ingredient6.isNullOrEmpty() && !drink.measure6.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient6, drink.measure6))
        }
        if (!drink.ingredient7.isNullOrEmpty() && !drink.measure7.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient7, drink.measure7))
        }
        if (!drink.ingredient8.isNullOrEmpty() && !drink.measure8.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient8, drink.measure8))
        }
        if (!drink.ingredient9.isNullOrEmpty() && !drink.measure9.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient9, drink.measure9))
        }
        if (!drink.ingredient10.isNullOrEmpty() && !drink.measure10.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient10, drink.measure10))
        }
        if (!drink.ingredient11.isNullOrEmpty() && !drink.measure11.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient11, drink.measure11))
        }
        if (!drink.ingredient12.isNullOrEmpty() && !drink.measure12.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient12, drink.measure12))
        }
        if (!drink.ingredient13.isNullOrEmpty() && !drink.measure13.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient13, drink.measure13))
        }
        if (!drink.ingredient14.isNullOrEmpty() && !drink.measure14.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient14, drink.measure14))
        }
        if (!drink.ingredient15.isNullOrEmpty() && !drink.measure15.isNullOrEmpty()) {
            ingredientsList.add(Ingredient(drink.ingredient15, drink.measure15))
        }

        return ingredientsList
    }
}