package com.example.hiltcleanarchitecture2.presentation.ui.photo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import com.example.hiltcleanarchitecture2.domain.usecase.GetPhotosUseCase

/**A helper class for the UI controller that is responsible for
 * preparing data for the UI [PhotosFragment]
 *
 * @author ZARA
 * */
class PhotosViewModel @ViewModelInject constructor(private val getPhotosUseCase: GetPhotosUseCase) : ViewModel() {

    private val TAG = PhotosViewModel::class.java.simpleName
    val photoListReceivedLiveData = MutableLiveData<List<PhotoEntity>>()
    val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = false
    }


    fun loadPhotos(id: Long?) {
        if (id == null) return
        getPhotosUseCase.saveAlbumId(id)
        getPhotosUseCase.execute(
            onSuccess = {
                isLoad.value = true
                photoListReceivedLiveData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}