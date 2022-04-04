package com.example.recipeapp.repo

import com.example.recipeapp.model.Category
import com.example.recipeapp.model.DetailId
import com.example.recipeapp.model.PopularFood

object Repository {

    private val apiServices by lazy {
        Network.getApiServices()
    }

    suspend fun getRandomMeal(): PopularFood {
        return apiServices.getBanner()
    }

    suspend fun getCatogryMeal(): Category {
        return apiServices.getCategoryMeal()
    }

    suspend fun getItemDetails(i: String): DetailId {
        return apiServices.getItemDetail(i)
    }

    suspend fun getProductDetails(categoryId: String): PopularFood {
        return apiServices.getProduct(categoryId)
    }

}