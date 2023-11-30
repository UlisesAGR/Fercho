/*
 * ReadMedicineUseCase.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.domain.usecase.detail

import com.example.clinicaldecisions.domain.repository.DetailRepository
import javax.inject.Inject

class ReadMedicineUseCase @Inject constructor(
    private val horoscopeRepository: DetailRepository,
) {
    suspend operator fun invoke(name: String) =
        horoscopeRepository.readMedicine(name)
}