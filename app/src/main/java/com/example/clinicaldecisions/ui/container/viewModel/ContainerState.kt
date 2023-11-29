/*
 * ContainerState.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.container.viewModel

import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.utils.FormEvents

data class ContainerState(
    val message: String = "",
    val list: List<MedicineModel> = emptyList(),
    val formEvent: FormEvents = FormEvents.NONE,
)