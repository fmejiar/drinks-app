package com.fmejiar.drinksapp.data.response.ingredients

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val name: String = "",
    val measure: String = ""
) : Parcelable