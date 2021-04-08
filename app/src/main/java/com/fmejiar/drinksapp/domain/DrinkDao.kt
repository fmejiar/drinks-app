package com.fmejiar.drinksapp.domain

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM drinkTable")
    fun getAllFavoriteDrinksListWithChanges(): LiveData<List<DrinkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteDrink(drinkEntity: DrinkEntity)

    @Delete
    suspend fun deleteFavoriteDrink(drinkEntity: DrinkEntity)

}