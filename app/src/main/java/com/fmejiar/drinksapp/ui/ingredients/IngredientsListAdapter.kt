package com.fmejiar.drinksapp.ui.ingredients

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fmejiar.drinksapp.base.BaseViewHolder
import com.fmejiar.drinksapp.databinding.IngredientRowBinding
import com.fmejiar.model.home.ingredients.Ingredient

class IngredientsListAdapter(
        private val context: Context
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var ingredientsList = listOf<Ingredient>()

    fun setIngredientsList(ingredientsList: List<Ingredient>) {
        this.ingredientsList = ingredientsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = IngredientRowBinding.inflate(LayoutInflater.from(context), parent, false)

        val holder = IngredientViewHolder(itemBinding)

        /*itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onDrinkClick(drinksList[position], position)
        }*/

        return holder
    }

    override fun getItemCount(): Int = ingredientsList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is IngredientViewHolder -> holder.bind(ingredientsList[position])
        }
    }

    private inner class IngredientViewHolder(
            private val binding: IngredientRowBinding
    ) : BaseViewHolder<Ingredient>(binding.root) {

        override fun bind(item: Ingredient) = with(binding) {

            /*Glide.with(context)
                    .load("https://www.thecocktaildb.com/images/ingredients/gin-Small.png")
                    .centerCrop()
                    .into(imgIngredient)*/
            Glide.with(context)
                    .load("https://www.thecocktaildb.com/images/ingredients/${item.name}-Small.png")
                    .centerCrop()
                    .into(imgIngredient)
            nameIngredient.text = item.name
            measureIngredient.text = item.measure

        }
    }

}