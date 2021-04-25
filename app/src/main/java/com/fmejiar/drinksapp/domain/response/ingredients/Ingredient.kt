package com.fmejiar.drinksapp.domain.response.ingredients

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val name: String = "",
    val measure: String = ""
) : Parcelable