package com.example.hiltcleanarchitecture2.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hiltcleanarchitecture2.Constants
import com.example.hiltcleanarchitecture2.data.base.ModelEntity

@Entity(tableName = Constants.Table.PHOTO)
data class PhotoEntity(
    @PrimaryKey var id: Long,
    var title: String,
    val url: String,
    val thumbnailUrl: String?
) : ModelEntity()
{
    fun setName(text: String) {
        title = text
    }
}