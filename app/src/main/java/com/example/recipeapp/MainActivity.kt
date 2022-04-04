package com.example.recipeapp

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recipeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
         var navController= findNavController(R.id.fragmentContainerView)

        actionBar?.hide()

        binding.bottomNavigation.setupWithNavController(navController)


//        binding.bottomNavigation.itemTextColor = ColorStateList.valueOf(Color.parseColor("#F76E11"));
//        binding.bottomNavigation.itemIconTintList = ColorStateList.valueOf(Color.parseColor("#F76E11"));
    }
}