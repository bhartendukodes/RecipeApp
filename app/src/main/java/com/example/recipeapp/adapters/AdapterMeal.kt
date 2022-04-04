package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipeapp.databinding.CategorieCardBinding
import com.example.recipeapp.model.Category

class AdapterMeal(private val onProductClick:(Int)->Unit):ListAdapter<Category.CategoryX, AdapterMeal.MyViewHolder>(MoviesDiffUtil) {

    inner class MyViewHolder(val binding: CategorieCardBinding):RecyclerView.ViewHolder(binding.root)

    object MoviesDiffUtil:DiffUtil.ItemCallback<Category.CategoryX>() {
        override fun areItemsTheSame(oldItem: Category.CategoryX, newItem: Category.CategoryX): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category.CategoryX, newItem: Category.CategoryX): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CategorieCardBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val categorie = getItem(position)

        holder.binding.apply {
            imgCategory.load(categorie.strCategoryThumb)
            tvCategoryName.text=categorie.strCategory


            root.setOnClickListener{
                onProductClick.invoke(position)
            }
        }
    }
}