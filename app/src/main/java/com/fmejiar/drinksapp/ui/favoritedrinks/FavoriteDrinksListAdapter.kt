package com.fmejiar.drinksapp.ui.favoritedrinks

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fmejiar.drinksapp.base.BaseViewHolder
import com.fmejiar.drinksapp.databinding.DrinkRowBinding
import com.fmejiar.model.home.drinks.Drink

class FavoriteDrinksListAdapter(
    private val context: Context,
    private val itemClickListener: OnFavoriteDrinkClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var favoriteDrinksList = listOf<Drink>()

    interface OnFavoriteDrinkClickListener {
        fun onFavoriteDrinkClick(drink: Drink, position: Int)
        fun onFavoriteDrinkLongClick(drink: Drink, position: Int)
    }

    fun setFavoriteDrinksList(favoriteDrinksList: List<Drink>) {
        this.favoriteDrinksList = favoriteDrinksList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = DrinkRowBinding.inflate(LayoutInflater.from(context), parent, false)

        val holder = DrinkViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onFavoriteDrinkClick(favoriteDrinksList[position], position)
        }

        itemBinding.root.setOnLongClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnLongClickListener true
            itemClickListener.onFavoriteDrinkLongClick(favoriteDrinksList[position], position)
            return@setOnLongClickListener true
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is DrinkViewHolder -> holder.bind(favoriteDrinksList[position])
        }
    }

    override fun getItemCount(): Int = favoriteDrinksList.size

    private inner class DrinkViewHolder(
        private val binding: DrinkRowBinding
    ) : BaseViewHolder<Drink>(binding.root) {

        override fun bind(item: Drink) = with(binding) {

            Glide.with(context)
                .load(item.image)
                .centerCrop()
                .into(imgDrink)
            nameDrink.text = item.name
            descriptionDrink.text = item.description

        }
    }

}
