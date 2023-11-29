/*
 * ContainerRepositoryImpl.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.data.repository

import com.example.clinicaldecisions.data.db.dao.MedicineDao
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.domain.model.toDomain
import com.example.clinicaldecisions.domain.model.toEntity
import com.example.clinicaldecisions.domain.repository.ContainerRepository
import com.example.clinicaldecisions.utils.ResponseStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContainerRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val medicineDao: MedicineDao,
) : ContainerRepository {

    override suspend fun getAllMedicines(): Flow<List<MedicineModel>> =
        medicineDao.getAllMedicines().map { list -> list.map { it.toDomain() } }

    override suspend fun createMedicine(article: MedicineModel) = flow {
        emit(ResponseStatus.Loading())
        try {
            emit(ResponseStatus.Success(medicineDao.createMedicine(article.toEntity())))
        } catch (ex: Exception) {
            emit(ResponseStatus.Error(ex.message))
        }
    }.flowOn(dispatcher)

    override suspend fun isExistingMedicine(name: String) = flow {
        emit(ResponseStatus.Loading())
        try {
            emit(ResponseStatus.Success(medicineDao.isExistingMedicine(name)))
        } catch (ex: Exception) {
            emit(ResponseStatus.Error(ex.message))
        }
    }.flowOn(dispatcher)

    override suspend fun updateMedicine(article: MedicineModel) = flow {
        emit(ResponseStatus.Loading())
        try {
            emit(ResponseStatus.Success(medicineDao.updateMedicine(article.toEntity())))
        } catch (ex: Exception) {
            emit(ResponseStatus.Error(ex.message))
        }
    }.flowOn(dispatcher)

    override suspend fun deleteMedicine(article: MedicineModel) = flow {
        emit(ResponseStatus.Loading())
        try {
            emit(ResponseStatus.Success(medicineDao.deleteMedicine(article.toEntity())))
        } catch (ex: Exception) {
            emit(ResponseStatus.Error(ex.message))
        }
    }.flowOn(dispatcher)
}