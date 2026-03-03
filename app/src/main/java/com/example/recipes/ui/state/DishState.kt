package com.example.recipes.ui.state

import com.example.recipes.data.network.dto.DishDto


data class DishUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val allDishes: List<DishDto> = emptyList(),
    val filteredDishes: List<DishDto> = emptyList(),
    val ingredientCategories: List<String> = emptyList(),
    val dishCategories: List<String> = emptyList(),
    val selectedIngredientCategory: String? = null, // null / "All"
    val selectedDishCategory: String? = null,       // null / "All"
    val searchQuery: String = ""
)