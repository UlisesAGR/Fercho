/*
 * ContainerRepositoryImpl.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.data.repository

import com.example.clinicaldecisions.data.source.ContainerDataSource
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.domain.repository.ContainerRepository
import com.example.clinicaldecisions.utils.ResponseStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContainerRepositoryImpl @Inject constructor(
    private val containerDataSource: ContainerDataSource,
    private val dispatcher: CoroutineDispatcher,
) : ContainerRepository {

    override suspend fun createMedicine(article: MedicineModel) = flow {
        emit(ResponseStatus.Loading())
        try {
            emit(ResponseStatus.Success(containerDataSource.createMedicine(article)))
        } catch (ex: Exception) {
            emit(ResponseStatus.Error(ex.message))
        }
    }.flowOn(dispatcher)

    override suspend fun readAllMedicines(): Flow<List<MedicineModel>> =
        containerDataSource.readAllMedicines()

    override suspend fun updateMedicine(article: MedicineModel) = flow {
        emit(ResponseStatus.Loading())
        try {
            emit(ResponseStatus.Success(containerDataSource.updateMedicine(article)))
        } catch (ex: Exception) {
            emit(ResponseStatus.Error(ex.message))
        }
    }.flowOn(dispatcher)

    override suspend fun deleteMedicine(article: MedicineModel) = flow {
        emit(ResponseStatus.Loading())
        try {
            emit(ResponseStatus.Success(containerDataSource.deleteMedicine(article)))
        } catch (ex: Exception) {
            emit(ResponseStatus.Error(ex.message))
        }
    }.flowOn(dispatcher)
}