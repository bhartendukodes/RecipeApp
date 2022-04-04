package com.example.recipeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipeapp.model.Meal

@Database(entities = [Meal::class], version = 1, exportSchema = false)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase:RoomDatabase() {

    abstract fun mealDao():MealDao

    companion object{
        private var INSTANCE:MealDatabase?=null

        fun getMealDatabase(applicationContext:Context):MealDatabase?{
            if (INSTANCE==null)
            {
                INSTANCE=Room.databaseBuilder(applicationContext, MealDatabase::class.java, "mealDb").build()
            }
            return INSTANCE
        }
    }
}