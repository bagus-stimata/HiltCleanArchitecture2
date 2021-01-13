package com.example.hiltcleanarchitecture2.domain.repository

import androidx.lifecycle.LiveData
import com.example.hiltcleanarchitecture2.data.source.local.entity.CocktailEntity
import com.example.hiltcleanarchitecture2.data.source.local.entity.asCocktailEntity
import com.example.hiltcleanarchitecture2.data.source.remote.api.NetworkDataSource
import com.example.hiltcleanarchitecture2.domain.usecase.GetCocktailUseCase_Asal
import com.example.hiltcleanarchitecture2.presentation.base.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Gastón Saillén on 10 August 2020
 */

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class DefaultCocktailRepositoryAsli @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val getCocktailUseCaseAsal: GetCocktailUseCase_Asal
) : CocktailRepository_Asli {

    override suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<CocktailEntity>>> =
        callbackFlow {

            offer(getCachedCocktails(cocktailName))

            networkDataSource.getCocktailByName(cocktailName).collect {
                when (it) {
                    is Resource.Success -> {
                        for (cocktail in it.data) {
                            saveCocktail(cocktail.asCocktailEntity())
                        }
                        offer(getCachedCocktails(cocktailName))
                    }
                    is Resource.Failure -> {
                        offer(getCachedCocktails(cocktailName))
                    }
                }
            }
            awaitClose { cancel() }
        }

    override suspend fun saveFavoriteCocktail(cocktailEntity: CocktailEntity) {
        getCocktailUseCaseAsal.saveFavoriteCocktail(cocktailEntity)
    }

    override suspend fun isCocktailFavorite(cocktailEntity: CocktailEntity): Boolean =
        getCocktailUseCaseAsal.isCocktailFavorite(cocktailEntity)

    override suspend fun saveCocktail(cocktailEntity: CocktailEntity) {
        getCocktailUseCaseAsal.saveCocktail(cocktailEntity)
    }

    override fun getFavoritesCocktails(): LiveData<List<CocktailEntity>> {
        return getCocktailUseCaseAsal.getFavoritesCocktails()
    }

    override suspend fun deleteFavoriteCocktail(cocktailEntity: CocktailEntity) {
        getCocktailUseCaseAsal.deleteCocktail(cocktailEntity)
    }

    override suspend fun getCachedCocktails(cocktailName: String): Resource<List<CocktailEntity>> {
        return getCocktailUseCaseAsal.getCachedCocktails(cocktailName)
    }
}