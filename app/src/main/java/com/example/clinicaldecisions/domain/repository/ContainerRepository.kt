/*
 * ContainerRepository.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.domain.repository

import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface ContainerRepository {
    suspend fun getAllMedicines(): Flow<List<MedicineModel>>
    suspend fun createMedicine(article: MedicineModel): Flow<ResponseStatus<Unit>>
    suspend fun isExistingMedicine(name: String): Flow<ResponseStatus<Boolean>>
    suspend fun updateMedicine(article: MedicineModel): Flow<ResponseStatus<Unit>>
    suspend fun deleteMedicine(article: MedicineModel): Flow<ResponseStatus<Unit>>
}