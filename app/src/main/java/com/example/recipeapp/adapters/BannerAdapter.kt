package com.example.recipeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ItemBannerBinding
import com.example.recipeapp.model.PopularFood

class BannerAdapter(private val onItemClick:(String)->Unit):ListAdapter<PopularFood.Meal,BannerAdapter.ViewHolder>(BannerDiffUtil) {


    inner class ViewHolder(val binding:ItemBannerBinding):RecyclerView.ViewHolder(binding.root)


    object BannerDiffUtil:DiffUtil.ItemCallback<PopularFood.Meal>(){
        override fun areItemsTheSame(
            oldItem: PopularFood.Meal,
            newItem: PopularFood.Meal
        ): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(
            oldItem: PopularFood.Meal,
            newItem: PopularFood.Meal
        ): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBannerBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val banner=getItem(position)


        holder.binding.apply {
            ivBanner.load(banner.strMealThumb){
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder)
            }
            root.setOnClickListener {
                onItemClick.invoke(banner.idMeal)
            }
        }
    }
}