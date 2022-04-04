package com.example.recipeapp.repo

import com.example.recipeapp.model.Category
import com.example.recipeapp.model.DetailId
import com.example.recipeapp.model.PopularFood
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("api/json/v1/1/filter.php?c=Seafood")
    suspend fun getBanner():PopularFood

    @GET("api/json/v1/1/categories.php")
    suspend fun getCategoryMeal():Category

    @GET("api/json/v1/1/lookup.php")
    suspend fun getItemDetail(
        @Query("i") i:String
    ):DetailId

    @GET("api/json/v1/1/filter.php")
    suspend fun getProduct(
        @Query("c") catgoryId:String
    ):PopularFood


}