package com.example.recipes.repository

import com.example.recipes.data.network.dto.DishDto


interface DishRepository {
    suspend fun fetchDishes(): List<DishDto>
}