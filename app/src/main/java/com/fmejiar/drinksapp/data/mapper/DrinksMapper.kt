package com.fmejiar.drinksapp.data.mapper

import com.fmejiar.drinksapp.data.response.drinks.DrinksResponse
import com.fmejiar.model.home.drinks.Drink
import com.fmejiar.model.home.ingredients.Ingredient
import com.fmejiar.model.home.drinks.DrinksModel

fun DrinksResponse.toDomain(): DrinksModel {
    return DrinksModel(
        drinksList = transformDrinksList(drinksList)
    )
}

private fun transformDrinksList(drinksList: List<com.fmejiar.drinksapp.data.response.drinks.Drink>): List<Drink> {
    val drinkList = ArrayList<Drink>()
    drinksList.forEach { drink ->
        drinkList.add(
            Drink(
                drink.id,
                drink.image ?: "",
                drink.name ?: "",
                drink.description ?: "",
                drink.hasAlcohol ?: "",
                drink.ingredient1 ?: "",
                drink.ingredient2 ?: "",
                drink.ingredient3 ?: "",
                drink.ingredient4 ?: "",
                drink.ingredient5 ?: "",
                drink.ingredient6 ?: "",
                drink.ingredient7 ?: "",
                drink.ingredient8 ?: "",
                drink.ingredient9 ?: "",
                drink.ingredient10 ?: "",
                drink.ingredient11 ?: "",
                drink.ingredient12 ?: "",
                drink.ingredient13 ?: "",
                drink.ingredient14 ?: "",
                drink.ingredient15 ?: "",
                drink.measure1 ?: "",
                drink.measure2 ?: "",
                drink.measure3 ?: "",
                drink.measure4 ?: "",
                drink.measure5 ?: "",
                drink.measure6 ?: "",
                drink.measure7 ?: "",
                drink.measure8 ?: "",
                drink.measure9 ?: "",
                drink.measure10 ?: "",
                drink.measure11 ?: "",
                drink.measure12 ?: "",
                drink.measure13 ?: "",
                drink.measure14 ?: "",
                drink.measure15 ?: "",
                ingredients = transformIngredientsList(drink.ingredients)
            )
        )
    }

    return drinkList
}

private fun transformIngredientsList(ingredientsList: MutableList<com.fmejiar.drinksapp.data.response.ingredients.Ingredient>): MutableList<Ingredient> {
    val ingredientList = ArrayList<Ingredient>()
    ingredientsList.forEach { ingredient ->
        ingredientList.add(
            Ingredient(ingredient.name, ingredient.measure)
        )
    }
    return ingredientList
}