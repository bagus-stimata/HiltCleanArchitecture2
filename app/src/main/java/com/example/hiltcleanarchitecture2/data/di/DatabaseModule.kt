package com.example.hiltcleanarchitecture2.data.di;

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.hiltcleanarchitecture2.Constants
import com.example.hiltcleanarchitecture2.data.source.local.AppDatabase
import com.example.hiltcleanarchitecture2.data.source.local.dao.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

//    @Provides
//    @Singleton
//    internal fun provideAppDatabase(application: Application): AppDatabase {
//        return Room.databaseBuilder(
//            application,
//            AppDatabase::class.java,
//            AppDatabase.DB_NAME
//        ).allowMainThreadQueries().build()
//    }
//    @Provides
//    internal fun providePhotoDao(appDatabase: AppDatabase): PhotoDao {
//        return appDatabase.photoDao
//    }

    @Provides
    @DatabaseInfo
    fun providerDatabaseName(): String {
        return Constants.Table.DB_NAME
    }
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context, @DatabaseInfo dbName: String) = AppDatabase.getDatabase(appContext, dbName)

    @Singleton
    @Provides
    fun providePhotoDao(db: AppDatabase): PhotoDao = db.photoDao

    @Singleton
    @Provides
    fun provideCocktailDao(db: AppDatabase) = db.cocktailDao()


}