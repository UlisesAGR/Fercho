/*
 * RepositoryModule.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.di

import com.example.clinicaldecisions.data.repository.ContainerRepositoryImpl
import com.example.clinicaldecisions.data.repository.DetailRepositoryImpl
import com.example.clinicaldecisions.domain.repository.ContainerRepository
import com.example.clinicaldecisions.domain.repository.DetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideContainerRepository(containerRepositoryImpl: ContainerRepositoryImpl): ContainerRepository

    @Singleton
    @Binds
    abstract fun provideDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}