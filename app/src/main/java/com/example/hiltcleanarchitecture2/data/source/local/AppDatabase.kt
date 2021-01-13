package com.example.hiltcleanarchitecture2.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hiltcleanarchitecture2.data.source.local.dao.CocktailDao
import com.example.hiltcleanarchitecture2.data.source.local.dao.PhotoDao
import com.example.hiltcleanarchitecture2.data.source.local.entity.CocktailEntity
import com.example.hiltcleanarchitecture2.data.source.local.entity.FavoritesEntity
import com.example.hiltcleanarchitecture2.data.source.local.entity.PhotoEntity

@Database(entities = [
    PhotoEntity::class,
    FavoritesEntity::class,
    CocktailEntity::class
], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val photoDao: PhotoDao
    abstract fun cocktailDao(): CocktailDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context, dbName: String): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context, dbName).also { instance = it } }

        private fun buildDatabase(appContext: Context, dbName: String) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, dbName)
                .fallbackToDestructiveMigration()
                .build()
    }

}