package com.example.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.recipes.data.network.api.KtorDishApi
import com.example.recipes.data.repository.DishRepositoryImpl
import com.example.recipes.ui.screens.DishScreen
import com.example.recipes.ui.viewmodel.DishViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val api = KtorDishApi(baseUrl = " https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/dev/nosh-assignment")
        val repository = DishRepositoryImpl(api)
        val viewmodel = DishViewModel(repository)
        setContent {
            DishScreen(viewModel = viewmodel)
        }
    }
}
