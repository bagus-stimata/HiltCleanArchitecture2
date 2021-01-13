package com.example.hiltcleanarchitecture2.data.source.remote.api

import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import com.example.hiltcleanarchitecture2.domain.model.Album
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("albums/")
    fun getAlbums(): Single<List<Album>>

    @GET("albums/{id}/photos")
    fun getPhotos(@Path("id") id: Long): Single<List<PhotoEntity>>

    @GET("photos/{id}")
    fun getPhotoDetail(@Path("id") id: Long):Single<PhotoEntity>
}