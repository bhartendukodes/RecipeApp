package com.example.recipeapp.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize


data class DetailId(
    val meals: List<Meal>
)