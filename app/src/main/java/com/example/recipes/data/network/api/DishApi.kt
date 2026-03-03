package com.example.recipes.data.network.api

import com.example.recipes.data.network.dto.DishDto

interface DishApi {
    suspend fun getDishes(): List<DishDto>
}