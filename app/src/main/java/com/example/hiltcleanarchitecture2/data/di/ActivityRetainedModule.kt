package com.example.hiltcleanarchitecture2.data.di

import com.example.hiltcleanarchitecture2.domain.repository.CocktailRepository_Asli
import com.example.hiltcleanarchitecture2.domain.repository.DefaultCocktailRepositoryAsli
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun dataSource(impl: DefaultCocktailRepositoryAsli): CocktailRepository_Asli
}