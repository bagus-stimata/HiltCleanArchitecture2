package com.example.hiltcleanarchitecture2.presentation.ui.photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltcleanarchitecture2.R
import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity
import com.example.hiltcleanarchitecture2.databinding.HolderPhotoBinding
import com.example.hiltcleanarchitecture2.presentation.extension.loadImage
import java.util.*


/**
 * [android.support.v7.widget.RecyclerView.Adapter] to adapt
 * [PhotoEntity] items into [RecyclerView] via [PhotoViewHolder]
 *
 *
 * Created by ZARA on 02/02/2019.
 */
internal class PhotosAdapter(val mListener: OnPhotosAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = PhotosAdapter::class.java.name
    private val photoEntities: MutableList<PhotoEntity> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPhotoBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.holder_photo, parent, false
        )
        return PhotoViewHolder(holderPhotoBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): PhotoEntity {
        return photoEntities[position]
    }

    override fun getItemCount(): Int {
        return photoEntities.size
    }

    fun addData(list: List<PhotoEntity>) {
        this.photoEntities.clear()
        this.photoEntities.addAll(list)
        notifyDataSetChanged()
    }


    inner class PhotoViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {


        fun onBind(photoEntity: PhotoEntity) {
            val holderPhotoBinding = dataBinding as HolderPhotoBinding
            holderPhotoBinding.photoViewModel = PhotoViewModel(photoEntity)
            holderPhotoBinding.photoProgressBar.visibility = View.VISIBLE
            holderPhotoBinding.photoImageView.loadImage(photoEntity.url, holderPhotoBinding.photoProgressBar)


            itemView.setOnClickListener {
                mListener.gotoDetailPage(holderPhotoBinding.photoImageView, photoEntity.id)
            }

        }
    }
}
