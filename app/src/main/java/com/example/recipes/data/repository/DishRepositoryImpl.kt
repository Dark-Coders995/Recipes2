package com.example.recipes.data.repository

import com.example.recipes.data.network.api.DishApi
import com.example.recipes.data.network.dto.DishDto
import com.example.recipes.repository.DishRepository

class DishRepositoryImpl(
    private val api: DishApi
) : DishRepository {
    override suspend fun fetchDishes(): List<DishDto> = api.getDishes()
}