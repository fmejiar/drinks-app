package com.fmejiar.drinksapp.ui.drinks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fmejiar.drinksapp.base.BaseViewHolder
import com.fmejiar.drinksapp.databinding.DrinkRowBinding
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import com.fmejiar.model.home.drinks.Drink

class DrinksListAdapter(
        private val itemClickListener: OnDrinkClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var drinksList = listOf<Drink>()

    interface OnDrinkClickListener {
        fun onDrinkClick(drink: Drink, position: Int)
    }

    fun setDrinksList(drinksList: List<Drink>) {
        this.drinksList = drinksList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = DrinkRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = DrinkViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onDrinkClick(drinksList[position], position)
        }

        return holder
    }

    override fun getItemCount(): Int = drinksList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is DrinkViewHolder -> holder.bind(drinksList[position])
        }
    }

    private inner class DrinkViewHolder(
            private val binding: DrinkRowBinding
    ) : BaseViewHolder<Drink>(binding.root) {

        override fun bind(item: Drink) = with(binding) {

            Glide.with(imgDrink.context)
                    .load(item.image)
                    .centerCrop()
                    .into(imgDrink)
            nameDrink.text = item.name
            descriptionDrink.text = item.description

        }
    }

}