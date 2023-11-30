/*
 * ReadAllMedicinesUseCase.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.domain.usecase.container

import com.example.clinicaldecisions.domain.repository.ContainerRepository
import javax.inject.Inject

class ReadAllMedicinesUseCase @Inject constructor(
    private val horoscopeRepository: ContainerRepository,
) {
    suspend operator fun invoke() = horoscopeRepository.readAllMedicines()
}