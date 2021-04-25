package com.fmejiar.drinksapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fmejiar.model.home.ingredients.Ingredient

@Entity(tableName = "drinkTable")
data class DrinkEntity(
        @PrimaryKey
        val id: String = "",
        @ColumnInfo(name = "drink_image")
        val image: String = "",
        @ColumnInfo(name = "drink_name")
        val name: String = "",
        @ColumnInfo(name = "drink_description")
        val description: String = "",
        @ColumnInfo(name = "drink_has_alcohol")
        val hasAlcohol: String = "Non_Alcoholic",
        @ColumnInfo(name = "ingredients")
        val ingredients: MutableList<Ingredient> = mutableListOf()
)


