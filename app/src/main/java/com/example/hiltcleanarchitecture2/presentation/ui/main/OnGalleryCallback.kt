package com.example.hiltcleanarchitecture2.presentation.ui.main

import android.widget.ImageView
import com.example.hiltcleanarchitecture2.domain.model.Album

/**
 * To make an interaction between [GalleryActivity] & its children
 * */
interface OnGalleryCallback {

    fun navigateToAlbumPage(album: Album)

    fun gotoDetailPageByPhotoId(imageView: ImageView, id: Long)
}