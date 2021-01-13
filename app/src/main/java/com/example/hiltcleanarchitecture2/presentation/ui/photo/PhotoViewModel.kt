package com.example.hiltcleanarchitecture2.presentation.ui.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity

class PhotoViewModel(val photoEntity: PhotoEntity) :ViewModel() {

    private val TAG = PhotoViewModel::class.java.name
    val photoData = MutableLiveData<PhotoEntity>()

    init {
        photoData.value = photoEntity
    }

}