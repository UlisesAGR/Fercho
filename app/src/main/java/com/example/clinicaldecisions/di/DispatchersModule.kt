/*
 * DispatchersModule.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}