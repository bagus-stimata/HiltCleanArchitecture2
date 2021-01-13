package com.example.hiltcleanarchitecture2.domain.usecase

import com.example.hiltcleanarchitecture2.domain.model.Album
import com.example.hiltcleanarchitecture2.domain.repository.AlbumRepository
import com.example.hiltcleanarchitecture2.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [AlbumViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetAlbumsUseCase @Inject constructor(private val repository: AlbumRepository) : SingleUseCase<List<Album>>() {


    override fun buildUseCaseSingle(): Single<List<Album>> {
        return repository.getAlbums()
    }
}