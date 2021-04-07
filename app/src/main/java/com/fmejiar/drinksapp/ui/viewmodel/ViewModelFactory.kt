package com.fmejiar.drinksapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fmejiar.drinksapp.domain.DrinkRepository

class ViewModelFactory(private val drinkRepository: DrinkRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DrinkRepository::class.java).newInstance(drinkRepository)
    }
}