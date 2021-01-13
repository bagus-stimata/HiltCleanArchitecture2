package com.example.hiltcleanarchitecture2.presentation.ui.detailphoto

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import com.example.hiltcleanarchitecture2.domain.usecase.GetPhotoDetailUseCase

/**
 * A helper class for the UI controller that is responsible for
 * preparing data for [PhotoDetailActivity]
 *
 * @author ZARA
 * */
class PhotoDetailViewModel @ViewModelInject constructor(private val getPhotoDetailUseCase: GetPhotoDetailUseCase) : ViewModel() {

    private val TAG = PhotoDetailViewModel::class.java.simpleName
    val photoData = MutableLiveData<PhotoEntity>()
    val isLoad = MutableLiveData<Boolean>()
    val isFavorite = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }

    fun getDetail(id: Long?) {
        if (id == null) return
        getPhotoDetailUseCase.savePhotoId(id)
        getPhotoDetailUseCase.execute(
            onSuccess = {
                isLoad.value = true
                photoData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun updateFavoriteStatus() {
        photoData.value?.let { photo ->
            if (isFavorite.value == true) {
                getPhotoDetailUseCase.deleteAsFavorite(photo)
            }else{
                getPhotoDetailUseCase.addAsFavorite(photo)
            }
            isFavorite.value?.let {
                isFavorite.value= !it
            }
        }
    }

    fun checkFavoriteStatus(photoId: Long) {
        isFavorite.value = getPhotoDetailUseCase.isFavorite(photoId)
    }
}