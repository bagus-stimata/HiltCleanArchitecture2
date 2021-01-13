package com.example.hiltcleanarchitecture2.domain.usecase

import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import com.example.hiltcleanarchitecture2.domain.repository.PhotoRepository
import com.example.hiltcleanarchitecture2.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of [PhotoViewModel](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetPhotoDetailUseCase @Inject constructor(private val repository: PhotoRepository) : SingleUseCase<PhotoEntity>() {

    private var photoId: Long? = null

    fun savePhotoId(id: Long) {
        photoId = id
    }

    override fun buildUseCaseSingle(): Single<PhotoEntity> {
        return repository.getPhotoDetail(photoId)
    }

    fun deleteAsFavorite(photoEntity: PhotoEntity) {
        repository.deletePhoto(photoEntity)
    }

    fun addAsFavorite(photoEntity: PhotoEntity) {
        repository.addPhoto(photoEntity)
    }

    fun isFavorite(photoId: Long): Boolean {
        return repository.isFavorite(photoId)
    }
}