package com.example.hiltcleanarchitecture2.data.source.remote.api

import com.example.hiltcleanarchitecture2.data.source.local.entity.CocktailList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gastón Saillén on 04 July 2020
 */
interface WebService {
    @GET("search.php")
    suspend fun getCocktailByName(@Query(value = "s") tragoName: String): CocktailList?
}