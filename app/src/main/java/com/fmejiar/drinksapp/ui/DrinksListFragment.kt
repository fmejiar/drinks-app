package com.fmejiar.drinksapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmejiar.drinksapp.AppDatabase
import com.fmejiar.drinksapp.R
import com.fmejiar.drinksapp.data.DrinkDataStoreImpl
import com.fmejiar.drinksapp.data.model.Drink
import com.fmejiar.drinksapp.databinding.FragmentDrinksListBinding
import com.fmejiar.drinksapp.domain.DrinkRepositoryImpl
import com.fmejiar.drinksapp.ui.viewmodel.DrinksListViewModel
import com.fmejiar.drinksapp.ui.viewmodel.ViewModelFactory
import com.fmejiar.drinksapp.vo.ResultType

class DrinksListFragment : Fragment(), DrinksListAdapter.OnDrinkClickListener {

    private lateinit var binding: FragmentDrinksListBinding
    private val viewModel by activityViewModels<DrinksListViewModel> {
        ViewModelFactory(
                DrinkRepositoryImpl
                (DrinkDataStoreImpl(AppDatabase.getDatabase(requireActivity().applicationContext)))
        )
    }
    private lateinit var drinkListAdapter: DrinksListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drinkListAdapter = DrinksListAdapter(requireContext(), this)
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
        setupGoToFavoriteDrinksButton()
    }

    private fun setupDrinksRecyclerView() {
        binding.rvDrinks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDrinks.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )
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

    private fun setupGoToFavoriteDrinksButton() {
        binding.btnGoToFavoriteDrinks.setOnClickListener {
            findNavController().navigate(R.id.action_drinksListFragment_to_favoriteDrinksListFragment)
        }
    }

    private fun setupObservers() {
        viewModel.fetchDrinksList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultType.Loading -> {
                    binding.pbarDrinks.visibility = View.VISIBLE
                }
                is ResultType.Success -> {
                    binding.pbarDrinks.visibility = View.GONE
                    drinkListAdapter.setDrinksList(result.data)
                }
                is ResultType.Failure -> {
                    binding.pbarDrinks.visibility = View.GONE
                    Toast.makeText(
                            requireContext(),
                            "Ocurrío un error al traer los datos ${result.exception}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onDrinkClick(drink: Drink, position: Int) {
        findNavController().navigate(
                DrinksListFragmentDirections.actionDrinksListFragmentToDrinkDetailFragment(drink)
        )
    }

}