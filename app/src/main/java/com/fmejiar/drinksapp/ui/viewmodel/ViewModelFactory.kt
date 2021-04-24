package com.fmejiar.drinksapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fmejiar.drinksapp.domain.usecase.*

class ViewModelFactory(private val getDrinksByNameUseCase: GetDrinksByNameUseCase,
                       private val insertRoomDrinkUseCase: InsertRoomDrinkUseCase,
                       private val getRoomFavoriteDrinksListUseCase: GetRoomFavoriteDrinksListUseCase,
                       private val deleteRoomFavoriteDrinkUseCase: DeleteRoomFavoriteDrinkUseCase,
                       private val verifyRoomFavoriteDrinkUseCase: VerifyRoomFavoriteDrinkUseCase,
                       private val getDrinkByIdUseCase: GetDrinkByIdUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
                GetDrinksByNameUseCase::class.java,
                InsertRoomDrinkUseCase::class.java,
                GetRoomFavoriteDrinksListUseCase::class.java,
                DeleteRoomFavoriteDrinkUseCase::class.java,
                VerifyRoomFavoriteDrinkUseCase::class.java,
                GetDrinkByIdUseCase::class.java)
                .newInstance(
                        getDrinksByNameUseCase,
                        insertRoomDrinkUseCase,
                        getRoomFavoriteDrinksListUseCase,
                        deleteRoomFavoriteDrinkUseCase,
                        verifyRoomFavoriteDrinkUseCase,
                        getDrinkByIdUseCase)
    }
}