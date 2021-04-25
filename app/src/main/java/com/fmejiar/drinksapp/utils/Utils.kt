package com.fmejiar.drinksapp.utils

import android.view.View
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.model.home.drinks.Drink

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