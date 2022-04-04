package com.example.recipeapp.database

import androidx.room.*
import com.example.recipeapp.model.Meal
import kotlinx.coroutines.flow.Flow
@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal:Meal)

    @Query("SELECT * FROM MEALINFORMATION")
    fun getAllMeals():Flow<List<Meal>>
}