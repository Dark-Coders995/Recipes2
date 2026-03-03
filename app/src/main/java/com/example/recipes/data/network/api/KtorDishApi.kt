package com.example.recipes.data.network.api

import com.example.recipes.data.network.dto.DishDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorDishApi(
    private val baseUrl: String
) : DishApi {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getDishes(): List<DishDto> {
        return client.get("$baseUrl").body()
    }

}
