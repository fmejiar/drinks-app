package com.fmejiar.drinksapp.data.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
// import com.fmejiar.drinksapp.domain.response.drinks.Drink
// import com.fmejiar.drinksapp.domain.response.ingredients.Ingredient
import com.fmejiar.model.home.drink.Drink
import com.fmejiar.model.home.ingredient.Ingredient
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/*@Parcelize
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
        val hasAlcohol: String = "Non_Alcoholic",
        @SerializedName("strIngredient1")
        val ingredient1: String = "",
        @SerializedName("strIngredient2")
        val ingredient2: String = "",
        @SerializedName("strIngredient3")
        val ingredient3: String = "",
        @SerializedName("strIngredient4")
        val ingredient4: String = "",
        @SerializedName("strIngredient5")
        val ingredient5: String = "",
        @SerializedName("strIngredient6")
        val ingredient6: String = "",
        @SerializedName("strIngredient7")
        val ingredient7: String = "",
        @SerializedName("strIngredient8")
        val ingredient8: String = "",
        @SerializedName("strIngredient9")
        val ingredient9: String = "",
        @SerializedName("strIngredient10")
        val ingredient10: String = "",
        @SerializedName("strIngredient11")
        val ingredient11: String = "",
        @SerializedName("strIngredient12")
        val ingredient12: String = "",
        @SerializedName("strIngredient13")
        val ingredient13: String = "",
        @SerializedName("strIngredient14")
        val ingredient14: String = "",
        @SerializedName("strIngredient15")
        val ingredient15: String = "",
        @SerializedName("strMeasure1")
        val measure1: String = "",
        @SerializedName("strMeasure2")
        val measure2: String = "",
        @SerializedName("strMeasure3")
        val measure3: String = "",
        @SerializedName("strMeasure4")
        val measure4: String = "",
        @SerializedName("strMeasure5")
        val measure5: String = "",
        @SerializedName("strMeasure6")
        val measure6: String = "",
        @SerializedName("strMeasure7")
        val measure7: String = "",
        @SerializedName("strMeasure8")
        val measure8: String = "",
        @SerializedName("strMeasure9")
        val measure9: String = "",
        @SerializedName("strMeasure10")
        val measure10: String = "",
        @SerializedName("strMeasure11")
        val measure11: String = "",
        @SerializedName("strMeasure12")
        val measure12: String = "",
        @SerializedName("strMeasure13")
        val measure13: String = "",
        @SerializedName("strMeasure14")
        val measure14: String = "",
        @SerializedName("strMeasure15")
        val measure15: String = "",
        var ingredients: MutableList<Ingredient> = mutableListOf()
) : Parcelable*/

/*data class DrinksResponse(
        @SerializedName("drinks")
        val drinksList: List<Drink> = listOf()
)*/

/*@Parcelize
data class Ingredient(
        val name: String = "",
        val measure: String = ""
) : Parcelable*/

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

fun List<DrinkEntity>.asDrinksList(): List<Drink> = this.map {
    Drink(it.id, it.image, it.name, it.description, it.hasAlcohol, ingredients = it.ingredients)
}

fun Drink.asDrinkEntity(): DrinkEntity =
        DrinkEntity(this.id, this.image, this.name, this.description, this.hasAlcohol)

/*class IngredientsTypeConverter {

   @TypeConverter
   fun toIngredientList(value: String): MutableList<Ingredient> {
       val listType = object : TypeToken<MutableList<Ingredient>>(){}.type
       return Gson().fromJson(value, listType)
   }

   @TypeConverter
   fun fromIngredientList(ingredientList: MutableList<Ingredient>): String {
       return Gson().toJson(ingredientList)
   }
}*/

class IngredientsTypeConverter {

        @TypeConverter
        fun jsonToList(value: String): List<Ingredient> = Gson().fromJson(value, Array<Ingredient>::class.java).toList()

        @TypeConverter
        fun listToJson(ingredientList: List<Ingredient>): String = Gson().toJson(ingredientList)

}