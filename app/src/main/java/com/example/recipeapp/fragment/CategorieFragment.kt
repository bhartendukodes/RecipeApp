package com.example.recipeapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.recipeapp.adapters.PagerAdapter
import com.example.recipeapp.databinding.FragmentCategorieBinding
import com.example.recipeapp.model.Category
import com.example.recipeapp.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class CategorieFragment : Fragment() {

    private val binding by lazy {
        FragmentCategorieBinding.inflate(layoutInflater)
    }

    private val homeViewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    private val args:CategorieFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observer()
        return binding.root
    }



    private fun observer() {
        homeViewModel.categoryMeal.observe(viewLifecycleOwner) {
            setAdapter(it.categories)
        }
    }

    private fun setAdapter(categories: List<Category.CategoryX>) {
        binding.apply {
            val adapter = PagerAdapter(requireActivity(),categories)
            binding.viewPager2.adapter = adapter

            TabLayoutMediator(tabLayout,viewPager2)
            { myTabLayout: TabLayout.Tab, position: Int ->
                myTabLayout.text = categories[position].strCategory
            }.attach()
            viewPager2.currentItem = args.position
            viewPager2.isSaveEnabled=false
        }
    }
}