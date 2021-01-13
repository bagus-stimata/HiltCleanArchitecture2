package com.example.hiltcleanarchitecture2.data.repository

import com.example.hiltcleanarchitecture2.data.source.local.AppDatabase
import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import com.example.hiltcleanarchitecture2.data.source.remote.api.RetrofitService
import com.example.hiltcleanarchitecture2.domain.repository.PhotoRepository
import io.reactivex.rxjava3.core.Single

/**
 * This repository is responsible for
 * fetching data [photo] from server or db
 * */
class PhotoRepositoryImp(
    private val database: AppDatabase,
    private val retrofitService: RetrofitService
) : PhotoRepository {

    override fun isFavorite(photoId: Long): Boolean {
        val loadOneByPhotoId = database.photoDao.loadOneByPhotoId(photoId)
        return loadOneByPhotoId != null
    }

    override fun deletePhoto(photoEntity: PhotoEntity) {
        database.photoDao.delete(photoEntity)
    }

    override fun addPhoto(photoEntity: PhotoEntity) {
        database.photoDao.insert(photoEntity)
    }


    override fun getPhotoDetail(photoId: Long?): Single<PhotoEntity> {
        return retrofitService.getPhotoDetail(photoId!!)
    }

    override fun getPhotos(albumId: Long?): Single<List<PhotoEntity>> {
        return retrofitService.getPhotos(albumId!!)
    }

}