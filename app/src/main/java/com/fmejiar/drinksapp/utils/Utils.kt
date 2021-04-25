package com.fmejiar.drinksapp.utils

import android.view.View
import androidx.room.TypeConverter
import com.fmejiar.drinksapp.data.local.DrinkEntity
import com.fmejiar.model.home.drinks.Drink
import com.fmejiar.model.home.ingredients.Ingredient
import com.google.gson.Gson

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

inline fun <T : View> T.showIf(condition: (T) -> Boolean) {
    if (condition(this)) {
        show()
    } else {
        hide()
    }
}

fun List<DrinkEntity>.asDrinksList(): List<Drink> = this.map {
    Drink(it.id, it.image, it.name, it.description, it.hasAlcohol, ingredients = it.ingredients)
}

fun Drink.asDrinkEntity(): DrinkEntity =
        DrinkEntity(this.id, this.image, this.name, this.description, this.hasAlcohol)

class IngredientsTypeConverter {

    @TypeConverter
    fun jsonToList(value: String): List<Ingredient> = Gson().fromJson(value, Array<Ingredient>::class.java).toList()

    @TypeConverter
    fun listToJson(ingredientList: List<Ingredient>): String = Gson().toJson(ingredientList)

}