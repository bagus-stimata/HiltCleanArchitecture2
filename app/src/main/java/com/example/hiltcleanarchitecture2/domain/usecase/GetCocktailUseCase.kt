package com.example.hiltcleanarchitecture2.domain.usecase

import androidx.lifecycle.LiveData
import com.example.hiltcleanarchitecture2.data.source.local.entity.CocktailEntity
import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import com.example.hiltcleanarchitecture2.domain.repository.CocktailRepository
import com.example.hiltcleanarchitecture2.domain.repository.PhotoRepository
import com.example.hiltcleanarchitecture2.domain.usecase.base.SingleUseCase
import com.example.hiltcleanarchitecture2.presentation.base.Resource
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of [PhotoViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetCocktailUseCase @Inject constructor(private val repository: CocktailRepository) : SingleUseCase<List<CocktailEntity>>() {

    private var photoId: Long? = null

    fun savePhotoId(id: Long) {
        photoId = id
    }

    override fun buildUseCaseSingle(): Single<List<CocktailEntity>> {
        return repository.getCocktails()
    }

    fun getCocktails(): Single<List<CocktailEntity>> {
        return repository.getCocktails()
    }
    fun getCocktailByName(cocktailName: String): Flow<Resource<List<CocktailEntity>>> {
        return repository.getCocktailByName(cocktailName!!)
    }
    fun saveFavoriteCocktail(cocktailEntity: CocktailEntity){
        return repository.saveFavoriteCocktail(cocktailEntity)
    }
    fun isCocktailFavorite(cocktailEntity: CocktailEntity): Boolean {
        return repository.isCocktailFavorite(cocktailEntity)
    }
    fun getCachedCocktails(cocktailName: String): Resource<List<CocktailEntity>> {
        return repository.getCachedCocktails(cocktailName)
    }
    fun saveCocktail(cocktailEntity: CocktailEntity){
        return repository.saveCocktail(cocktailEntity)
    }
    fun getFavoritesCocktails(): LiveData<List<CocktailEntity>> {
        return repository.getFavoritesCocktails()
    }
    fun deleteFavoriteCocktail(cocktailEntity: CocktailEntity){
        return repository.deleteFavoriteCocktail(cocktailEntity)
    }

}