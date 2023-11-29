/*
 * RoomModule.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.di

import android.content.Context
import androidx.room.Room
import com.example.clinicaldecisions.data.db.ClinicalDecisionsDb
import com.example.clinicaldecisions.data.db.dao.MedicineDao
import com.example.clinicaldecisions.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ClinicalDecisionsDb::class.java,
            DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMedicineDao(dataBase: ClinicalDecisionsDb): MedicineDao =
        dataBase.getMedicineDao()
}