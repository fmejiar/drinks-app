package com.fmejiar.drinksapp.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.fmejiar.model.home.drinks.Drink
import com.fmejiar.model.home.ingredients.Ingredient
import com.google.gson.Gson

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