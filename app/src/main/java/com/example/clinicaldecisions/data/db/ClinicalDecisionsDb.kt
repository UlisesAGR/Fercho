/*
 * ClinicalDecisionsDatabase.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clinicaldecisions.data.db.dao.MedicineDao
import com.example.clinicaldecisions.data.db.entities.MedicineEntity

@Database(
    entities = [MedicineEntity::class],
    version = 1,
    exportSchema = false,
)

abstract class ClinicalDecisionsDb : RoomDatabase() {
    abstract fun getMedicineDao(): MedicineDao
}