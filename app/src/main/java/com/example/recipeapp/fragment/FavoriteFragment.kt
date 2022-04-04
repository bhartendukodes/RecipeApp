package com.example.recipeapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.adapters.FavoriteMealAdapter
import com.example.recipeapp.databinding.FragmentFavoriteBinding
import com.example.recipeapp.viewmodel.HomeViewModel
import com.example.recipeapp.viewmodel.HomeViewModelFactory
import com.example.recipeapp.viewmodel.MealViewModel
import com.example.recipeapp.viewmodel.MealViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteFragment : Fragment() {

    private val binding by lazy {
        FragmentFavoriteBinding.inflate(layoutInflater)
    }

    private val homeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory(requireContext()))[HomeViewModel::class.java]
    }

    private val mealViewModel by lazy {
        ViewModelProvider(this, MealViewModelFactory(requireContext()))[MealViewModel::class.java]
    }

    private val mealAdapter by lazy {
        FavoriteMealAdapter(
            {
                val action = FavoriteFragmentDirections.actionFavoriteFragment2ToDetailFragment(it)
                findNavController().navigate(action)
            },
            {
                homeViewModel.deleteMeal(it)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observer()
        initView()
        onSwipeDelete()
        return binding.root
    }

    private fun onSwipeDelete() {
        val itemTouchHelper = object :ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        )
        {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                    mealAdapter.deleteItem(position)

                Snackbar.make(requireView(), "Meal Deleted", Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    View.OnClickListener {
                    }
                ).show()
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFavoritePRoducts)
    }
    private fun observer() {
        lifecycleScope.launch {
            mealViewModel.getMeal().collectLatest {
                withContext(Dispatchers.Main)
                {
                    mealAdapter.submitList(it)
                }
            }

        }
    }

    private fun initView() {
        binding.apply {
            rvFavoritePRoducts.adapter = mealAdapter
        }
    }

}

