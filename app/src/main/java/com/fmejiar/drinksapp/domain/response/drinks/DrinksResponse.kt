package com.fmejiar.drinksapp.domain.response.drinks

import com.google.gson.annotations.SerializedName

data class DrinksResponse(
    @SerializedName("drinks")
    val drinksList: List<Drink> = listOf()
)