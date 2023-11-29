/*
 * MedicineModel.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.domain.model

import com.example.clinicaldecisions.data.db.entities.MedicineEntity

data class MedicineModel(
    val id: Int = 0,
    val name: String,
    val description: String,
    val image: String,
)

fun MedicineEntity.toDomain(): MedicineModel = MedicineModel(id, name, description, image)
fun MedicineModel.toEntity(): MedicineEntity = MedicineEntity(id, name, description, image)