package com.alkamali.newsapp.data.remote

import com.alkamali.newsapp.domain.model.ApiResponse
import com.alkamali.newsapp.domain.model.ItemEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/news/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int? = 1
    ): ApiResponse

    @GET("news/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse

}

val data = ApiResponse(
    success = true,
    entityList = listOf(
        ItemEntity(
            id = 1,
            name = "Superman",
            image = "/images/img-1.png",
            about = "A superhero with incredible powers.",
            rating = 3.5,
            power = 100,
            month = "April",
            day = "18",
            family = listOf("Lois Lane", "Jonathan Kent", "Martha Kent"),
            abilities = listOf("Flight", "Super Strength", "Heat Vision"),
            natureTypes = listOf("Kryptonian")
        ),
        ItemEntity(
            id = 3,
            name = "Wonder Woman",
            image = "/images/img-1.png",
            about = "A warrior princess of the Amazons.",
            rating = 2.2,
            power = 95,
            month = "February",
            day = "21",
            family = listOf("Steve Trevor", "Hippolyta"),
            abilities = listOf("Super Strength", "Combat Skills", "Lasso of Truth"),
            natureTypes = listOf("Amazonian")
        ),
        ItemEntity(
            id = 4,
            name = "Flash",
            image = "/images/img-1.png",
            about = "The fastest man alive.",
            rating = 4.8,
            power = 90,
            month = "September",
            day = "22",
            family = listOf("Iris West", "Wally West"),
            abilities = listOf("Super Speed", "Time Travel", "Vibration"),
            natureTypes = listOf("Meta-human")
        )
    )

)
