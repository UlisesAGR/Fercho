/*
 * DetailState.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.detail.viewModel

import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.utils.DetailEvents

data class DetailState(
    val loading: Boolean = false,
    val message: String = "",
    val detailEvents: DetailEvents = DetailEvents.NONE,
    val data: MedicineModel = MedicineModel(
        id = 0,
        name = "",
        description = "",
        image = "",
    ),
)