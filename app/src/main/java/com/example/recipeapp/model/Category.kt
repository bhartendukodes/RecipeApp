package com.example.recipeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class  Category(
    val categories: List<CategoryX>
):Parcelable
{
    @Parcelize
    data class CategoryX(
        val idCategory: String,
        val strCategory: String,
        val strCategoryDescription: String,
        val strCategoryThumb: String
    ):Parcelable
}