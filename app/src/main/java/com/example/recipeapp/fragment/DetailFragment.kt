package com.example.recipeapp.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentDetailBinding
import com.example.recipeapp.model.DetailId
import com.example.recipeapp.model.Meal
import com.example.recipeapp.viewmodel.HomeViewModel
import com.example.recipeapp.viewmodel.HomeViewModelFactory
import com.example.recipeapp.viewmodel.MealViewModel
import com.example.recipeapp.viewmodel.MealViewModelFactory


class DetailFragment : Fragment() {

    private lateinit var youTubeLink: String

    private val binding by lazy {
       FragmentDetailBinding.inflate(layoutInflater)
    }

    private var meal: Meal? = null

    private val args: DetailFragmentArgs by navArgs()

    private val homeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory(requireContext()))[HomeViewModel::class.java]
    }

    private val mealViewModel by lazy {
        ViewModelProvider(this, MealViewModelFactory(requireContext()))[MealViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        WindowInsetsControllerCompat(
            requireActivity().window,
            requireActivity().window.decorView
        ).isAppearanceLightStatusBars = true
        requireActivity().window.statusBarColor = ContextCompat.getColor(
            requireContext(),
            R.color.orange
        )
        onLoading()
        observer()
        getDetail()
        onClick()
        return binding.root
    }

    private fun onClick() {
        binding.floatingButton.setOnClickListener {
            if (meal != null) {
                mealViewModel.insertMeal(meal!!)
                Toast.makeText(requireContext(), "MealSaved", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observer() {
        homeViewModel.itemDetails.observe(viewLifecycleOwner)
        {
            onSuccessfullyComplete()
            initUi(it)
        }
    }

    private fun initUi(it: DetailId?) {
        binding.apply {
            if (it != null) {
                meal = it.meals[0]
                ctTitle.title = it.meals[0].strMeal
                tvCategoryName.text = it.meals[0].strCategory
                tvArea.text = "Area :"+it.meals[0].strArea
                tvDesc.text = it.meals[0].strInstructions
                ivImage.load(it.meals[0].strMealThumb)
                youTubeLink = it.meals[0].strYoutube.toString()

                val meal=it.meals[0]
                val strIngridents = meal.strIngredient1?.plus("\n")
                    .plus((meal.strIngredient2?:"").plus("\n"))
                    .plus((meal.strIngredient3?:"").plus("\n"))
                    .plus((meal.strIngredient3?:"").plus("\n"))
                    .plus((meal.strIngredient4?:"").plus("\n"))
                    .plus((meal.strIngredient5?:"").plus("\n"))
                    .plus((meal.strIngredient6?:"").plus("\n"))
                    .plus((meal.strIngredient7?:"").plus("\n"))
                    .plus((meal.strIngredient8?:"").plus("\n"))
                    .plus((meal.strIngredient9?:"").plus("\n"))
                    .plus((meal.strIngredient10?:"").plus("\n"))
                    .plus((meal.strIngredient11?:"").plus("\n"))
                    .plus((meal.strIngredient12?:"").plus("\n"))
                    .plus((meal.strIngredient13?:"").plus("\n"))
                    .plus((meal.strIngredient14?:"").plus("\n"))
                    .plus((meal.strIngredient15?:"").plus("\n"))
                    .plus(meal.strIngredient16?:"").plus("\n")
                    .plus(meal.strIngredient17?:"").plus("\n")
                    .plus(meal.strIngredient18?:"").plus("\n")
                    .plus(meal.strIngredient19?:"").plus("\n")
                    .plus(meal.strIngredient20?:"").plus("\n")
                tvIngridentName.text = strIngridents

                val strMeasure = meal.strMeasure1?.plus("\n")
                    .plus(meal.strMeasure2?.plus("\n"))
                    .plus(meal.strMeasure3?.plus("\n"))
                    .plus(meal.strMeasure4?.plus("\n"))
                    .plus(meal.strMeasure5?.plus("\n"))
                    .plus(meal.strMeasure6?.plus("\n"))
                    .plus(meal.strMeasure7?.plus("\n"))
                    .plus(meal.strMeasure8?.plus("\n"))
                    .plus(meal.strMeasure9?.plus("\n"))
                    .plus(meal.strMeasure10?.plus("\n"))
                    .plus(meal.strMeasure11?.plus("\n"))
                    .plus(meal.strMeasure12?.plus("\n"))
                    .plus(meal.strMeasure13?.plus("\n"))
                    .plus(meal.strMeasure14?.plus("\n"))
                    .plus(meal.strMeasure15?.plus("\n"))
                    .plus(meal.strIngredient16?:"").plus("\n")
                    .plus(meal.strIngredient17?:"").plus("\n")
                    .plus(meal.strIngredient18?:"").plus("\n")
                    .plus(meal.strIngredient19?:"").plus("\n")
                    .plus(meal.strIngredient20?:"").plus("\n")

                tvQuantityName.text= strMeasure




                binding.ivYoutube.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youTubeLink))
                    startActivity(intent)
                }
            }
        }
    }


    private fun getDetail() {
        homeViewModel.getItemDetails(args.id)
    }


    private fun onLoading() {
        binding.apply {
            progressBar2.visibility = View.VISIBLE
            floatingButton.visibility = View.INVISIBLE
            ivCategory.visibility = View.INVISIBLE
            tvCategory.visibility = View.INVISIBLE
            tvCategoryName.visibility = View.INVISIBLE
            ivArea.visibility = View.INVISIBLE
            tvArea.visibility = View.INVISIBLE
            tvAreaView.visibility = View.INVISIBLE
            tvTitle.visibility = View.INVISIBLE
            ivLine.visibility = View.INVISIBLE
            tvDesc.visibility = View.INVISIBLE
            ivYoutube.visibility = View.INVISIBLE
        }
    }

    private fun onSuccessfullyComplete() {
        binding.apply {
            progressBar2.visibility = View.INVISIBLE
            floatingButton.visibility = View.VISIBLE
            ivCategory.visibility = View.VISIBLE
            tvCategory.visibility = View.VISIBLE
            tvCategoryName.visibility = View.VISIBLE
            ivArea.visibility = View.VISIBLE
            tvArea.visibility = View.VISIBLE
            tvAreaView.visibility = View.VISIBLE
            tvTitle.visibility = View.VISIBLE
            ivLine.visibility = View.VISIBLE
            tvDesc.visibility = View.VISIBLE
            ivYoutube.visibility = View.VISIBLE
        }
    }
}

