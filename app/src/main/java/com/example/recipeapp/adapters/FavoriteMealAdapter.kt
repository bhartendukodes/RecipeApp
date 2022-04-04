package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ItemFavBinding
import com.example.recipeapp.databinding.ItemproductBinding
import com.example.recipeapp.model.Meal
import com.example.recipeapp.model.PopularFood

class FavoriteMealAdapter( private val onItemClick:(String)->Unit, private val onDelete:(Meal)->Unit):ListAdapter<Meal, FavoriteMealAdapter.MyViewHolder>(MoviesDiffUtil) {

    inner class MyViewHolder(val binding: ItemFavBinding):RecyclerView.ViewHolder(binding.root)

    object MoviesDiffUtil:DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemFavBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val categorie = getItem(position)

        holder.binding.apply {
            ivProductImage.load(categorie.strMealThumb)
            tvProducts.text=categorie.strMeal

            root.setOnClickListener {
                onItemClick.invoke(categorie.idMeal.toString())
            }
        }

    }
    fun deleteItem(position: Int){
        onDelete.invoke(currentList[position])
    }

}