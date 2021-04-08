package com.fmejiar.drinksapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.fmejiar.drinksapp.AppDatabase
import com.fmejiar.drinksapp.R
import com.fmejiar.drinksapp.data.DrinkDataStoreImpl
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.data.model.DrinkEntity
import com.fmejiar.drinksapp.databinding.FragmentDrinkDetailBinding
import com.fmejiar.drinksapp.domain.DrinkRepositoryImpl
import com.fmejiar.drinksapp.ui.viewmodel.DrinksListViewModel
import com.fmejiar.drinksapp.ui.viewmodel.ViewModelFactory

class DrinkDetailFragment : Fragment() {

    private val viewModel by activityViewModels<DrinksListViewModel> {
        ViewModelFactory(
            DrinkRepositoryImpl
                (DrinkDataStoreImpl(AppDatabase.getDatabase(requireActivity().applicationContext)))
        )
    }
    private lateinit var binding: FragmentDrinkDetailBinding
    private lateinit var drink: Drink
    private val args: DrinkDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    }

    private fun setupUI() {
        Glide.with(requireContext())
            .load(drink.image)
            .centerCrop()
            .into(binding.imgDrink)
        binding.nameDrink.text = drink.name
        binding.descriptionDrink.text = drink.description
        if (drink.hasAlcohol.equals("Non_Alcoholic")) {
            binding.hasAlcoholDrink.text = "Non-alcoholic drink"
        } else {
            binding.hasAlcoholDrink.text = "Alcoholic drink"
        }
        binding.btnSaveDrink.setOnClickListener {
            viewModel.insertRoomDrink(
                DrinkEntity(
                    drink.id,
                    drink.image,
                    drink.name,
                    drink.description,
                    drink.hasAlcohol
                )
            )
            Toast.makeText(requireContext(), "The drink was saved in favorites", Toast.LENGTH_SHORT)
                .show()
        }

    }

}