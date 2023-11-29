/*
 * DetailRepositoryImpl.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.data.repository

import com.example.clinicaldecisions.data.db.dao.MedicineDao
import com.example.clinicaldecisions.domain.model.toDomain
import com.example.clinicaldecisions.domain.repository.DetailRepository
import com.example.clinicaldecisions.utils.ResponseStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val medicineDao: MedicineDao,
) : DetailRepository {

    override suspend fun getMedicine(name: String) = flow {
        emit(ResponseStatus.Loading())
        try {
            emit(ResponseStatus.Success(medicineDao.getMedicine(name).toDomain()))
        } catch (ex: Exception) {
            emit(ResponseStatus.Error(ex.message))
        }
    }.flowOn(dispatcher)
}