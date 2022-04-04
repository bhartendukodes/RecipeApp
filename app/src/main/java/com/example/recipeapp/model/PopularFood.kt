package com.example.recipeapp.model

data class PopularFood(
    val meals: List<Meal>
) {
    data class Meal(
        val idMeal: String,
        val strMeal: String,
        val strMealThumb: String
    )

}