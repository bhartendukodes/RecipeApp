package com.example.recipeapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recipeapp.adapters.ProductMealAdapter
import com.example.recipeapp.databinding.FragmentProductsBinding
import com.example.recipeapp.viewmodel.HomeViewModel
import com.example.recipeapp.viewmodel.HomeViewModelFactory

private const val CategoryId = "param1"

class ProductsFragment : Fragment() {

    private var categoryId: String? = null

    private val binding by lazy {
        FragmentProductsBinding.inflate(layoutInflater)
    }

    private val homeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory(requireContext()))[HomeViewModel::class.java]
    }

    private val adapters by lazy {
        ProductMealAdapter{
            val action = CategorieFragmentDirections.actionCategorieFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryId = it.getString(CategoryId)
            Log.d("Tag", "CategoryId: $categoryId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observer()
        getMeals()
        initView()
        return binding.root
    }

    private fun observer() {
        homeViewModel.productDetails.observe(viewLifecycleOwner)
        {
            adapters.submitList(it.meals)
        }
    }

    private fun getMeals() {
        homeViewModel.getProducts(categoryId ?: "seafood")
    }

    private fun initView() {
        binding.apply {
            val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvProducts.layoutManager = layoutManager
            rvProducts.adapter = adapters
        }

    }

    companion object {
        fun newInstance(param1: String) = ProductsFragment().apply {
            arguments = Bundle().apply {
                putString(CategoryId, param1)
            }
        }
    }
}