package com.fmejiar.drinksapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fmejiar.drinksapp.utils.IngredientsTypeConverter


@Database(entities = [DrinkEntity::class], version = 1, exportSchema = false)
@TypeConverters(IngredientsTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun drinkDao(): DrinkDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "drinkDatabase"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}