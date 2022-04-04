package com.example.recipeapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.RepositoryDb
import com.example.recipeapp.model.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MealViewModel(context: Context):ViewModel() {

    private val repo:RepositoryDb = RepositoryDb(context)


    fun getMeal():Flow<List<Meal>>
    {
        return repo.getMeal()
    }

    fun insertMeal(meal: Meal)
    {

        try {
            viewModelScope.launch {
                repo.insertMeal(meal)
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }

   fun deleteMeal(meal: Meal)
    {
        viewModelScope.launch {
            repo.deleteMeal(meal)
        }
    }
}