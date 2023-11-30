/*
 * DetailDataSource.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.data.source

import com.example.clinicaldecisions.data.db.dao.MedicineDao
import com.example.clinicaldecisions.domain.model.toDomain
import javax.inject.Inject

class DetailDataSource @Inject constructor(
    private val medicineDao: MedicineDao,
) {
    fun readMedicine(name: String) =
        medicineDao.readMedicine(name).toDomain()
}