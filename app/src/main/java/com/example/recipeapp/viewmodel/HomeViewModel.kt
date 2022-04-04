package com.example.recipeapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.database.RepositoryDb
import com.example.recipeapp.model.Category
import com.example.recipeapp.model.DetailId
import com.example.recipeapp.model.Meal
import com.example.recipeapp.model.PopularFood
import com.example.recipeapp.repo.Repository
import kotlinx.coroutines.launch
import java.lang.Exception


class HomeViewModel(context: Context) : ViewModel() {

    val randomMeal = MutableLiveData<PopularFood>()
    val categoryMeal = MutableLiveData<Category>()
    val itemDetails = MutableLiveData<DetailId>()
    val productDetails = MutableLiveData<PopularFood>()

    private val repos: RepositoryDb = RepositoryDb(context)

    fun getRandomMeal() {
        try {
            viewModelScope.launch {
                val dish = Repository.getRandomMeal()
                randomMeal.postValue(dish)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getCataogriesMeal() {
        try {
            viewModelScope.launch {
                try {
                    val dish2 = Repository.getCatogryMeal()
                    categoryMeal.postValue(dish2)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getItemDetails(i: String) {
        try {
            viewModelScope.launch {
                val dish3 = Repository.getItemDetails(i)
                itemDetails.postValue(dish3)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getProducts(categoryId: String) {
        try {
            viewModelScope.launch {
                val dish4 = Repository.getProductDetails(categoryId)
                productDetails.postValue(dish4)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun insertMeal(meal: Meal) {
        viewModelScope.launch {
            repos.insertMeal(meal)
        }
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch {
            repos.deleteMeal(meal)
        }
    }
}
