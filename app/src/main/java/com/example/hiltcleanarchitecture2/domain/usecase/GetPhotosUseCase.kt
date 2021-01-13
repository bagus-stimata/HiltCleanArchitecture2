package com.example.hiltcleanarchitecture2.domain.usecase

import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import com.example.hiltcleanarchitecture2.domain.repository.PhotoRepository
import com.example.hiltcleanarchitecture2.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


/**
 * An interactor that calls the actual implementation of [PhotosViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) : SingleUseCase<List<PhotoEntity>>() {

    private var albumId: Long? = null

    fun saveAlbumId(id: Long) {
        albumId = id
    }

    override fun buildUseCaseSingle(): Single<List<PhotoEntity>> {
        return repository.getPhotos(albumId)
    }
}