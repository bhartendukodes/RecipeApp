package com.example.recipeapp.database

import android.content.Context
import com.example.recipeapp.model.Meal
import kotlinx.coroutines.flow.Flow

class RepositoryDb(context: Context) {

    private val mealDao = MealDatabase.getMealDatabase(context)?.mealDao()

    fun getMeal(): Flow<List<Meal>> {
        return mealDao!!.getAllMeals()
    }

    suspend fun insertMeal(meal: Meal) {
        mealDao?.insertMeal(meal)
    }

    suspend fun deleteMeal(meal: Meal)
    {
        mealDao?.deleteMeal(meal)
    }
}