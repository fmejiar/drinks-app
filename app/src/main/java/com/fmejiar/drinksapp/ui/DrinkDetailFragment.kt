package com.fmejiar.drinksapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fmejiar.drinksapp.AppDatabase
import com.fmejiar.drinksapp.R
import com.fmejiar.drinksapp.data.DrinkDataStoreImpl
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.databinding.FragmentDrinkDetailBinding
import com.fmejiar.drinksapp.domain.*
import com.fmejiar.drinksapp.ui.viewmodel.DrinksListViewModel
import com.fmejiar.drinksapp.ui.viewmodel.ViewModelFactory
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
                        (DrinkDataStoreImpl(AppDatabase.getDatabase(requireActivity().applicationContext))))

        )
    }
    private lateinit var binding: FragmentDrinkDetailBinding
    private lateinit var drink: Drink
    private val args: DrinkDetailFragmentArgs by navArgs()
    private var isDrinkFavorited: Boolean? = null
    private lateinit var ingredientListAdapter: IngredientsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ingredientListAdapter = IngredientsListAdapter(requireContext())

        requireArguments().let {
            drink = args.drink
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
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        Glide.with(requireContext())
                .load(drink.image)
                .centerCrop()
                .into(binding.imgDrink)
        binding.nameDrink.text = drink.name
        binding.descriptionDrink.text = drink.description
        binding.btnSaveOrDeleteDrink.setOnClickListener {
            val isDrinkFavorited = isDrinkFavorited ?: return@setOnClickListener

            if (isDrinkFavorited) {
                viewModel.deleteRoomFavoriteDrink(drink)
                Toast.makeText(requireContext(), "The drink was deleted in favorites", Toast.LENGTH_SHORT)
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
                Toast.makeText(requireContext(), "The drink was saved in favorites", Toast.LENGTH_SHORT)
                        .show()
            }
            this.isDrinkFavorited = !isDrinkFavorited
            updateButtonIcon()
        }

        setupIngredientsRecyclerView()
        fetchIngredients()
    }

    fun updateButtonIcon() {
        val isDrinkFavorited = isDrinkFavorited ?: return
        binding.btnSaveOrDeleteDrink.setImageResource(
                when {
                    isDrinkFavorited -> R.drawable.ic_baseline_delete_24
                    else -> R.drawable.ic_baseline_save_24
                }
        )
    }

    fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            isDrinkFavorited = viewModel.isDrinkFavorite(drink)
            updateButtonIcon()
        }
    }

    private fun setupIngredientsRecyclerView() {
        binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIngredients.adapter = ingredientListAdapter
    }

    private fun fetchIngredients() {
        ingredientListAdapter.setIngredientsList(drink.ingredients)
    }

}