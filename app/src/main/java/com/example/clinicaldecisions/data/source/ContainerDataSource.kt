/*
 * ContainerDataSource.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.data.source

import com.example.clinicaldecisions.data.db.dao.MedicineDao
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.domain.model.toDomain
import com.example.clinicaldecisions.domain.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ContainerDataSource @Inject constructor(
    private val medicineDao: MedicineDao,
) {
    fun createMedicine(article: MedicineModel) {
        medicineDao.createMedicine(article.toEntity())
    }

    fun readAllMedicines(): Flow<List<MedicineModel>> =
        medicineDao.readAllMedicines().map { it.map { list -> list.toDomain() } }

    fun updateMedicine(article: MedicineModel) {
        medicineDao.updateMedicine(article.toEntity())
    }

    fun deleteMedicine(article: MedicineModel) {
        medicineDao.deleteMedicine(article.toEntity())
    }
}