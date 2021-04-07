package com.fmejiar.drinksapp.ui.viewmodel

import androidx.lifecycle.*
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.domain.DrinkRepository
import com.fmejiar.drinksapp.vo.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrinksListViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {

    private val drinkSearchName = MutableLiveData<String>()

    fun setDrinkSearchName(drinkName: String) {
        drinkSearchName.value = drinkName
    }

    init {
        setDrinkSearchName("margarita")
    }

    val fetchDrinksList = drinkSearchName.distinctUntilChanged().switchMap { drinkSearchName ->
        liveData(Dispatchers.IO) {
            emit(ResultType.Loading)
            try {
                emit(drinkRepository.getDrinksList(drinkSearchName))
            } catch (e: Exception) {
                emit(ResultType.Failure(e))
            }
        }
    }

    fun insertRoomDrink(drinkEntity: DrinkEntity) {
        viewModelScope.launch {
            drinkRepository.insertRoomDrink(drinkEntity)
        }
    }

    fun getRoomFavoriteDrinksList() = liveData(Dispatchers.IO) {
        emit(ResultType.Loading)
        try {
            emit(drinkRepository.getRoomFavoriteDrinksList())
        } catch (e: Exception) {
            emit(ResultType.Failure(e))
        }
    }

}