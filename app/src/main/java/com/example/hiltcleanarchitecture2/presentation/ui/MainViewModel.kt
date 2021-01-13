package com.example.hiltcleanarchitecture2.presentation.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.hiltcleanarchitecture2.data.source.local.entity.CocktailEntity
import com.example.hiltcleanarchitecture2.domain.repository.CocktailRepository_Asli
import com.example.hiltcleanarchitecture2.presentation.base.Resource
import com.example.hiltcleanarchitecture2.presentation.utils.ToastHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Gastón Saillén on 03 July 2020
 */

class MainViewModel @ViewModelInject constructor(
    private val repositoryAsli: CocktailRepository_Asli,
    private val toastHelper: ToastHelper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currentCocktailName = savedStateHandle.getLiveData<String>("cocktailName", "margarita")

    fun setCocktail(cocktailName: String) {
        currentCocktailName.value = cocktailName
    }

    val fetchCocktailList = currentCocktailName.distinctUntilChanged().switchMap { cocktailName ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                repositoryAsli.getCocktailByName(cocktailName).collect {
                    emit(it)
                }
            } catch (e: Exception) {
//                emit(Resource.Failure(e))
            }
        }
    }

    fun saveOrDeleteFavoriteCocktail(cocktailEntity: CocktailEntity) {
        viewModelScope.launch {
            if (repositoryAsli.isCocktailFavorite(cocktailEntity)) {
                repositoryAsli.deleteFavoriteCocktail(cocktailEntity)
                toastHelper.sendToast("Cocktail deleted from favorites")
            } else {
                repositoryAsli.saveFavoriteCocktail(cocktailEntity)
                toastHelper.sendToast("Cocktail saved to favorites")
            }
        }
    }

    fun getFavoriteCocktails() =
        liveData<Resource<List<CocktailEntity>>>(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emitSource(repositoryAsli.getFavoritesCocktails().map { Resource.Success(it) })
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    fun deleteFavoriteCocktail(cocktailEntity: CocktailEntity) {
        viewModelScope.launch {
            repositoryAsli.deleteFavoriteCocktail(cocktailEntity)
            toastHelper.sendToast("Cocktail deleted from favorites")
        }
    }

    suspend fun isCocktailFavorite(cocktailEntity: CocktailEntity): Boolean =
        repositoryAsli.isCocktailFavorite(cocktailEntity)
}