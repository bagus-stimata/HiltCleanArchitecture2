package com.example.hiltcleanarchitecture2.data.source.local.dao


import androidx.room.*
import com.example.hiltcleanarchitecture2.Constants
import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity

/**
 * it provides access to [PhotoEntity] underlying database
 * */
@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photoEntity: PhotoEntity): Long

    @Query("SELECT * FROM ${Constants.Table.PHOTO}")
    fun loadAll(): MutableList<PhotoEntity>

    @Delete
    fun delete(photoEntity: PhotoEntity)

    @Query("DELETE FROM ${Constants.Table.PHOTO}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.Table.PHOTO} where id = :photoId")
    fun loadOneByPhotoId(photoId: Long): PhotoEntity?

    @Query("SELECT * FROM ${Constants.Table.PHOTO} where title = :photoTitle")
    fun loadOneByPhotoTitle(photoTitle: String): PhotoEntity?

    @Update
    fun update(photoEntity: PhotoEntity)

}