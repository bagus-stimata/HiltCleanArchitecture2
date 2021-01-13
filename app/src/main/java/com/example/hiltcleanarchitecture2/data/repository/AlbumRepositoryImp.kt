package com.example.hiltcleanarchitecture2.data.repository

import com.example.hiltcleanarchitecture2.data.source.remote.api.RetrofitService
import com.example.hiltcleanarchitecture2.domain.model.Album
import com.example.hiltcleanarchitecture2.domain.repository.AlbumRepository
import io.reactivex.rxjava3.core.Single


/**
 * This repository is responsible for
 * fetching data[Album] from server or db
 * */
class AlbumRepositoryImp(
    private val retrofitService: RetrofitService
) :
    AlbumRepository {


    override fun getAlbums(): Single<List<Album>> {
        return retrofitService.getAlbums()
    }


}