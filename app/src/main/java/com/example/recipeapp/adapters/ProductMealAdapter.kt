package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ItemproductBinding
import com.example.recipeapp.model.PopularFood

class ProductMealAdapter(val onItemClicked:(String)->Unit):ListAdapter<PopularFood.Meal, ProductMealAdapter.MyViewHolder>(MoviesDiffUtil) {

    inner class MyViewHolder(val binding: ItemproductBinding):RecyclerView.ViewHolder(binding.root)

    object MoviesDiffUtil:DiffUtil.ItemCallback<PopularFood.Meal>() {
        override fun areItemsTheSame(oldItem: PopularFood.Meal, newItem: PopularFood.Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: PopularFood.Meal, newItem: PopularFood.Meal): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemproductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val categorie = getItem(position)

        holder.binding.apply {
            ivProductImage.load(categorie.strMealThumb)
            tvProducts.text=categorie.strMeal

            root.setOnClickListener {
                onItemClicked.invoke(categorie.idMeal)
            }
        }

    }
}