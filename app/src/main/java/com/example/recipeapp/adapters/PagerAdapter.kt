package com.example.recipeapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.recipeapp.fragment.*
import com.example.recipeapp.model.Category
import java.util.*

class PagerAdapter(fragmentActivity: FragmentActivity, val categories: List<Category.CategoryX>):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return ProductsFragment.newInstance(categories[position].strCategory)
    }
}