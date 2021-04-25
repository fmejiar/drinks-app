package com.fmejiar.drinksapp.ui.drinkdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fmejiar.drinksapp.data.local.AppDatabase
import com.fmejiar.drinksapp.R
import com.fmejiar.drinksapp.data.local.DrinkEntity
import com.fmejiar.drinksapp.data.repository.DrinkRepositoryImpl
import com.fmejiar.drinksapp.data.service.DrinkDataStoreImpl
import com.fmejiar.drinksapp.databinding.FragmentDrinkDetailBinding
import com.fmejiar.drinksapp.domain.*
import com.fmejiar.drinksapp.domain.usecase.*
import com.fmejiar.drinksapp.ui.ingredients.IngredientsListAdapter
import com.fmejiar.drinksapp.ui.viewmodel.DrinksListViewModel
import com.fmejiar.drinksapp.ui.viewmodel.ViewModelFactory
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.model.home.drinks.Drink
import com.fmejiar.model.home.ingredients.Ingredient
import kotlinx.coroutines.launch

class DrinkDetailFragment : Fragment() {

    private val viewModel by activityViewModels<DrinksListViewModel> {
        ViewModelFactory(
                GetDrinksByNameUseCase(
                        DrinkRepositoryImpl
                        (DrinkDataStoreImpl(AppDatabase.getDatabase(requireActivity().applicationContext)))),
                InsertRoomDrinkUseCase(
                        DrinkRepositoryImpl
                        (DrinkDataStoreImpl(AppDatabase.getDatabase(requireActivity().applicationContext)))),
                GetRoomFavoriteDrinksListUseCase(
                        DrinkRepositoryImpl
                        (DrinkDataStoreImpl(AppDatabase.getDatabase(requireActivity().applicationContext)))),
                DeleteRoomFavoriteDrinkUseCase(
                        DrinkRepositoryImpl
                        (DrinkDataStoreImpl(AppDatabase.getDatabase(requireActivity().applicationContext)))),
                VerifyRoomFavoriteDrinkUseCase(
                        DrinkRepositoryImpl
                        (DrinkDataStoreImpl(AppDatabase.getDatabase(requireActivity().applicationContext)))),
                GetDrinkByIdUseCase(
                        DrinkRepositoryImpl
                        (DrinkDataStoreImpl(AppDatabase.getDatabase(requireActivity().applicationContext))))
        )
    }
    private lateinit var binding: FragmentDrinkDetailBinding
    private lateinit var drink: Drink
    private var drinkId = ""
    private val args: DrinkDetailFragmentArgs by navArgs()
    private var isDrinkFavorited: Boolean? = null
    private lateinit var ingredientListAdapter: IngredientsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ingredientListAdapter = IngredientsListAdapter(requireContext())

        requireArguments().let {
            drinkId = args.drinkId
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drink_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDrinkDetailBinding.bind(view)
        // setupUI()
        setupObservers()
    }

    private fun setupUI(drink: Drink) {
        Glide.with(requireContext())
                .load(drink.image)
                .centerCrop()
                .into(binding.imgDrink)
        binding.nameDrink.text = drink.name
        binding.descriptionDrink.text = drink.description
        setupIngredientsRecyclerView()
        fetchIngredients(drink.ingredients)
        doSaveOrDeleteDrink()
    }

    private fun doSaveOrDeleteDrink() {
        binding.btnSaveOrDeleteDrink.setOnClickListener {
            val isDrinkFavorited = isDrinkFavorited ?: return@setOnClickListener

            if (isDrinkFavorited) {
                viewModel.deleteRoomFavoriteDrink(drink)
                Toast.makeText(requireContext(), "The drinks was deleted in favorites", Toast.LENGTH_SHORT)
                        .show()
            } else {
                viewModel.insertRoomDrink(
                        DrinkEntity(
                                drink.id,
                                drink.image,
                                drink.name,
                                drink.description,
                                drink.hasAlcohol,
                                drink.ingredients
                        )
                )
                Toast.makeText(requireContext(), "The drinks was saved in favorites", Toast.LENGTH_SHORT)
                        .show()
            }
            this.isDrinkFavorited = !isDrinkFavorited
            updateButtonIcon()
        }
    }

    private fun updateButtonIcon() {
        val isDrinkFavorited = isDrinkFavorited ?: return
        binding.btnSaveOrDeleteDrink.setImageResource(
                when {
                    isDrinkFavorited -> R.drawable.ic_baseline_delete_24
                    else -> R.drawable.ic_baseline_save_24
                }
        )
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            isDrinkFavorited = viewModel.isDrinkFavorite(drinkId)
            updateButtonIcon()
        }
        viewModel.getDrinkById(drinkId).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultType.Loading -> {

                }
                is ResultType.Success -> {
                    drink = result.data.first()
                    setupUI(drink)
                }
                is ResultType.Failure -> {

                }
            }
        })
    }

    private fun setupIngredientsRecyclerView() {
        binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIngredients.adapter = ingredientListAdapter
    }

    private fun fetchIngredients(ingredientsList: MutableList<Ingredient>) {
        ingredientListAdapter.setIngredientsList(ingredientsList)
    }

}