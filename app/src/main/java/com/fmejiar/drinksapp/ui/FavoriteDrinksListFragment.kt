package com.fmejiar.drinksapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmejiar.drinksapp.AppDatabase
import com.fmejiar.drinksapp.R
import com.fmejiar.drinksapp.data.DrinkDataStoreImpl
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.databinding.FragmentFavoriteDrinksListBinding
import com.fmejiar.drinksapp.domain.*
import com.fmejiar.drinksapp.ui.viewmodel.DrinksListViewModel
import com.fmejiar.drinksapp.ui.viewmodel.ViewModelFactory
import com.fmejiar.drinksapp.utils.show
import com.fmejiar.drinksapp.vo.ResultType

class FavoriteDrinksListFragment : Fragment(),
    FavoriteDrinksListAdapter.OnFavoriteDrinkClickListener {

    private lateinit var binding: FragmentFavoriteDrinksListBinding
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
    private lateinit var favoriteDrinksListAdapter: FavoriteDrinksListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteDrinksListAdapter = FavoriteDrinksListAdapter(requireContext(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_drinks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoriteDrinksListBinding.bind(view)
        setupUI()
        setupObservers()

    }

    private fun setupUI() {
        setupFavoriteDrinksRecyclerView()
    }

    private fun setupFavoriteDrinksRecyclerView() {
        binding.rvFavoriteDrinks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoriteDrinks.adapter = favoriteDrinksListAdapter
    }

    private fun setupObservers() {
        viewModel.getRoomFavoriteDrinksList().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultType.Loading -> {

                }
                is ResultType.Success -> {
                    if (result.data.isEmpty()) {
                        binding.emptyContainer.root.show()
                        return@Observer
                    }
                    favoriteDrinksListAdapter.setFavoriteDrinksList(result.data)
                }
                is ResultType.Failure -> {
                }
            }
        })
    }

    override fun onFavoriteDrinkClick(drink: Drink, position: Int) {
        findNavController().navigate(
            FavoriteDrinksListFragmentDirections.actionFavoriteDrinksListFragmentToDrinkDetailFragment(
                drink
            )
        )
    }

    override fun onFavoriteDrinkLongClick(drink: Drink, position: Int) {
        viewModel.deleteRoomFavoriteDrink(drink)
    }
}