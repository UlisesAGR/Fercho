/*
 * DetailRepository.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.domain.repository

import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getMedicine(name: String): Flow<ResponseStatus<MedicineModel>>
}