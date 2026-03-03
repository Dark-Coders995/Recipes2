package com.example.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.repository.DishRepository
import com.example.recipes.ui.state.DishUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale


class DishViewModel(
    private val repository: DishRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DishUiState(isLoading = true))
    val uiState: StateFlow<DishUiState> = _uiState

    init {
        loadDishes()
    }

    private fun loadDishes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val dishes = repository.fetchDishes()

                val ingredientCats = dishes
                    .map { it.ingredientCategory }
                    .distinct()
                    .sorted()

                val dishCats = dishes
                    .map { it.dishCategory }
                    .distinct()
                    .sorted()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        allDishes = dishes,
                        ingredientCategories = listOf("All") + ingredientCats,
                        dishCategories = listOf("All") + dishCats,
                        selectedIngredientCategory = "All",
                        selectedDishCategory = "All"
                    )
                }
                applyFilters()
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.localizedMessage ?: "Failed to load dishes"
                    )
                }
            }
        }
    }

    fun onIngredientCategorySelected(category: String) {
        _uiState.update { it.copy(selectedIngredientCategory = category) }
        applyFilters()
    }

    fun onDishCategorySelected(category: String) {
        _uiState.update { it.copy(selectedDishCategory = category) }
        applyFilters()
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        val query = state.searchQuery.trim().lowercase(Locale.getDefault())

        val filtered = state.allDishes.filter { dish ->
            val ingredientMatches =
                state.selectedIngredientCategory == null ||
                        state.selectedIngredientCategory == "All" ||
                        dish.ingredientCategory == state.selectedIngredientCategory

            val dishCatMatches =
                state.selectedDishCategory == null ||
                        state.selectedDishCategory == "All" ||
                        dish.dishCategory == state.selectedDishCategory

            val searchMatches =
                query.isEmpty() ||
                        dish.dishName.lowercase(Locale.getDefault()).contains(query) ||
                        dish.dishCategory.lowercase(Locale.getDefault()).contains(query) ||
                        dish.ingredientCategory.lowercase(Locale.getDefault()).contains(query)

            ingredientMatches && dishCatMatches && searchMatches
        }

        _uiState.update { it.copy(filteredDishes = filtered) }
    }
}