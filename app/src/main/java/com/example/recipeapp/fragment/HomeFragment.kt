package com.example.recipeapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.recipeapp.R
import com.example.recipeapp.adapters.AdapterMeal
import com.example.recipeapp.adapters.BannerAdapter
import com.example.recipeapp.databinding.FragmentHomeBinding
import com.example.recipeapp.viewmodel.HomeViewModel
import com.example.recipeapp.viewmodel.HomeViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity() , HomeViewModelFactory(requireContext()))[HomeViewModel::class.java]
    }
    private val categoryMealAdapter by lazy {
        AdapterMeal { position ->
            val action = HomeFragmentDirections.actionHomeFragmentToCategorieFragment(position)
            findNavController().navigate(action)
        }
    }

    private val bannerAdapter by lazy {
        BannerAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        WindowInsetsControllerCompat(
            requireActivity().window,
            requireActivity().window.decorView
        ).isAppearanceLightStatusBars = true
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.white);
        initView()
        observer()
        getMeal()
        return binding.root
    }


    private fun observer() {
        homeViewModel.randomMeal.observe(viewLifecycleOwner)
        {
            binding.apply {
                bannerAdapter.submitList(it.meals)
                Log.e("TAG", "Banner Dot ")


                TabLayoutMediator(binding.tabBanner, binding.vpBanner) { position, tab ->
                }.attach()
            }
        }
        homeViewModel.categoryMeal.observe(viewLifecycleOwner) {
            categoryMealAdapter.submitList(it.categories)

//
        }
    }

    private fun getMeal() {
        homeViewModel.getRandomMeal()
        homeViewModel.getCataogriesMeal()
    }

    private fun initView() {
        binding.apply {
            val layoutManager: RecyclerView.LayoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            rvCategori.layoutManager = layoutManager
            rvCategori.adapter = categoryMealAdapter
            vpBanner.adapter = bannerAdapter
            autoScroll(vpBanner)
        }
    }

    private fun autoScroll(viewPager: ViewPager2) {
        lifecycleScope.launchWhenCreated {
            while (true) {
                delay(6000)
                val totalItem = viewPager.adapter?.itemCount ?: 0

                if (totalItem != 0) {
                    withContext(Dispatchers.Main) {
                        if (viewPager.currentItem == totalItem - 1) {
                            viewPager.setCurrentItem(0, true)
                        } else {
                            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
                        }
                    }
                }
            }

        }

    }
}