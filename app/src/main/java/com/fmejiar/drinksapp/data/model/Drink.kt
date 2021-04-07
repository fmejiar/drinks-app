package com.fmejiar.drinksapp.data.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val id: String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non_Alcoholic"
) : Parcelable

data class DrinksResponse(
    @SerializedName("drinks")
    val drinksList: List<Drink> = listOf()
)

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
    val hasAlcohol: String = "Non_Alcoholic"
)