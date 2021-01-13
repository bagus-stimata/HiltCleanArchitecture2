package com.example.hiltcleanarchitecture2.domain.repository

import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import io.reactivex.rxjava3.core.Single


/**
 * To make an interaction between [PhotoRepositoryImp] & [GetPhotosUseCase]
 * */
interface PhotoRepository {

    fun getPhotos(albumId: Long?): Single<List<PhotoEntity>>

    fun getPhotoDetail(photoId: Long?): Single<PhotoEntity>

    fun deletePhoto(photoEntity: PhotoEntity)

    fun addPhoto(photoEntity: PhotoEntity)

    fun isFavorite(photoId: Long): Boolean
}