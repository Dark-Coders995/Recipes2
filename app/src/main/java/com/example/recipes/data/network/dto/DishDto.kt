package com.example.recipes.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DishDto(
    @SerialName("dishName") val dishName: String,
    @SerialName("dishId") val dishId: String,
    @SerialName("imageUrl") val imageUrl: String,
    @SerialName("isVeg") val isVeg: Boolean,
    @SerialName("isPublished") val isPublished: Boolean,
    @SerialName("Time") val timeMinutes: String,
    @SerialName("dishCategory") val dishCategory: String,
    @SerialName("IngredientCategory") val ingredientCategory: String
)
