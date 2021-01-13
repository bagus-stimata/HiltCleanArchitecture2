package com.example.hiltcleanarchitecture2.domain.repository

import androidx.lifecycle.LiveData
import com.example.hiltcleanarchitecture2.data.source.local.entity.Cocktail
import com.example.hiltcleanarchitecture2.data.source.local.entity.CocktailEntity
import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import com.example.hiltcleanarchitecture2.domain.model.Album
import com.example.hiltcleanarchitecture2.presentation.base.Resource
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow


/**
 * To make an interaction between [PhotoRepositoryImp] & [GetPhotosUseCase]
 * */
interface CocktailRepository {
    fun getCocktails(): Single<List<CocktailEntity>>
    fun getCocktailByName(cocktailName: String): Flow<Resource<List<CocktailEntity>>>
    fun saveFavoriteCocktail(cocktailEntity: CocktailEntity)
    fun isCocktailFavorite(cocktailEntity: CocktailEntity): Boolean
    fun getCachedCocktails(cocktailName: String): Resource<List<CocktailEntity>>
    fun saveCocktail(cocktailEntity: CocktailEntity)
    fun getFavoritesCocktails(): LiveData<List<CocktailEntity>>
    fun deleteFavoriteCocktail(cocktailEntity: CocktailEntity)
}