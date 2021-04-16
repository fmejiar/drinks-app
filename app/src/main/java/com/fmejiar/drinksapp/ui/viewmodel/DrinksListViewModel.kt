package com.fmejiar.drinksapp.ui.viewmodel

import androidx.lifecycle.*
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.domain.*
import com.fmejiar.drinksapp.vo.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DrinksListViewModel(private val getDrinksByNameUseCase: GetDrinksByNameUseCase,
                          private val insertRoomDrinkUseCase: InsertRoomDrinkUseCase,
                          private val getRoomFavoriteDrinksListUseCase: GetRoomFavoriteDrinksListUseCase,
                          private val deleteRoomFavoriteDrinkUseCase: DeleteRoomFavoriteDrinkUseCase,
                          private val verifyRoomFavoriteDrinkUseCase: VerifyRoomFavoriteDrinkUseCase) : ViewModel() {

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
                emit(getDrinksByNameUseCase.invoke(drinkSearchName))
            } catch (e: Exception) {
                emit(ResultType.Failure(e))
            }
        }
    }

    fun insertRoomDrink(drinkEntity: DrinkEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                insertRoomDrinkUseCase.invoke(drinkEntity)
            }
        }
    }

    fun getRoomFavoriteDrinksList() =
            liveData<ResultType<List<Drink>>>(viewModelScope.coroutineContext + Dispatchers.IO) {
                emit(ResultType.Loading)
                try {
                    emitSource(
                            getRoomFavoriteDrinksListUseCase.invoke().map { ResultType.Success(it) })
                } catch (e: Exception) {
                    emit(ResultType.Failure(e))
                }
            }

    fun deleteRoomFavoriteDrink(drink: Drink) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteRoomFavoriteDrinkUseCase.invoke(drink)
            }
        }
    }

    suspend fun isDrinkFavorite(drink: Drink): Boolean =
            verifyRoomFavoriteDrinkUseCase.invoke(drink)

}