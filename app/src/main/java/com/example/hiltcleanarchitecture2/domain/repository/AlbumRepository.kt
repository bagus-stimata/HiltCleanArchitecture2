package com.example.hiltcleanarchitecture2.domain.repository

import com.example.hiltcleanarchitecture2.domain.model.Album
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface AlbumRepository {

    fun getAlbums(): Single<List<Album>>
}