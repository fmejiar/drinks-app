package com.fmejiar.drinksapp.ui.drinks

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmejiar.drinksapp.data.local.AppDatabase
import com.fmejiar.drinksapp.R
import com.fmejiar.drinksapp.data.repository.DrinkRepositoryImpl
import com.fmejiar.drinksapp.data.service.DrinkDataStoreImpl
import com.fmejiar.drinksapp.databinding.FragmentDrinksListBinding
import com.fmejiar.drinksapp.domain.*
import com.fmejiar.drinksapp.domain.usecase.*
import com.fmejiar.drinksapp.ui.viewmodel.DrinksListViewModel
import com.fmejiar.drinksapp.ui.viewmodel.ViewModelFactory
import com.fmejiar.drinksapp.utils.hide
import com.fmejiar.drinksapp.utils.show
import com.fmejiar.drinksapp.utils.showIf
import com.fmejiar.drinksapp.vo.ResultType
import com.fmejiar.model.home.drinks.Drink

class DrinksListFragment : Fragment(), DrinksListAdapter.OnDrinkClickListener {

    private lateinit var binding: FragmentDrinksListBinding
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
    private val drinkListAdapter by lazy {
        DrinksListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drinks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDrinksListBinding.bind(view)
        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        setupDrinksRecyclerView()
        setupDrinksSearchView()
    }

    private fun setupDrinksRecyclerView() {
        binding.rvDrinks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDrinks.adapter = drinkListAdapter
    }

    private fun setupDrinksSearchView() {
        binding.svDrinks.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setDrinkSearchName(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupObservers() {
        viewModel.fetchDrinksList.observe(viewLifecycleOwner, Observer { result ->
            binding.sflDrinks.startShimmer()
            binding.sflDrinks.showIf { result is ResultType.Loading }
            when (result) {
                is ResultType.Loading -> {
                    binding.emptyContainer.root.hide()
                    binding.rvDrinks.hide()
                }
                is ResultType.Success -> {
                    binding.sflDrinks.stopShimmer()
                    binding.sflDrinks.hide()
                    if (result.data.isEmpty()) {
                        binding.rvDrinks.hide()
                        binding.emptyContainer.root.show()
                        return@Observer
                    }
                    binding.rvDrinks.show()
                    drinkListAdapter.setDrinksList(result.data)
                    binding.emptyContainer.root.hide()
                }
                is ResultType.Failure -> {
                    binding.sflDrinks.stopShimmer()
                    binding.sflDrinks.hide()
                    Toast.makeText(
                            requireContext(),
                            "An error occurred while fetching the data ${result.exception}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite_drinks -> {
                findNavController().navigate(R.id.action_drinksListFragment_to_favoriteDrinksListFragment)
                false
            }
            else -> false
        }
    }

    override fun onDrinkClick(drink: Drink, position: Int) {
        findNavController().navigate(
                DrinksListFragmentDirections.actionDrinksListFragmentToDrinkDetailFragment(drink.id)
        )
    }

}