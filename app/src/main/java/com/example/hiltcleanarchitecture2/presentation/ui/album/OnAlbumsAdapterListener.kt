package com.example.hiltcleanarchitecture2.presentation.ui.album

import com.example.hiltcleanarchitecture2.domain.model.Album

/**
 * To make an interaction between [AlbumsAdapter] & [AlbumsFragment]
 * */
interface OnAlbumsAdapterListener {

    fun showPhotos(album: Album)
}