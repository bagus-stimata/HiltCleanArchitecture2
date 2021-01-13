package com.example.hiltcleanarchitecture2.presentation.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltcleanarchitecture2.R
import com.example.hiltcleanarchitecture2.domain.model.Album
import com.example.hiltcleanarchitecture2.presentation.ui.album.AlbumsFragment
import com.example.hiltcleanarchitecture2.presentation.ui.detailphoto.PhotoDetailActivity
import com.example.hiltcleanarchitecture2.presentation.ui.photo.PhotosFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity(), OnGalleryCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        if (savedInstanceState == null) {
            navigateToGalleryPage()
        }
    }

    //Pertama dipanggil
    private fun navigateToGalleryPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.gallery_container,
                AlbumsFragment.newInstance(),
                AlbumsFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }

    override fun navigateToAlbumPage(album: Album) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.gallery_container,
                PhotosFragment.newInstance(album.id),
                PhotosFragment.FRAGMENT_NAME
            )
            .addToBackStack(PhotosFragment.FRAGMENT_NAME)
            .commitAllowingStateLoss()
    }


    override fun gotoDetailPageByPhotoId(imageView: ImageView, id: Long) {

        val intent = Intent(this, PhotoDetailActivity::class.java)
        val bundle = Bundle().apply {
            putLong(KEY_PHOTO_ID, id)
        }
        intent.putExtras(bundle)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imageView,
            ViewCompat.getTransitionName(imageView) ?: ""
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }


    companion object {
        private val KEY_PHOTO_ID = "KEY_PHOTO_ID"
    }
}
