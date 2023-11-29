/*
 * MedicineDao.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.clinicaldecisions.data.db.entities.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Query("SELECT * FROM medicine_table WHERE name=:name")
    fun getMedicine(name: String): MedicineEntity

    @Query("SELECT * FROM medicine_table")
    fun getAllMedicines(): Flow<List<MedicineEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createMedicine(article: MedicineEntity)

    @Query("SELECT * FROM medicine_table WHERE name=:name")
    fun isExistingMedicine(name: String): Boolean

    @Update
    fun updateMedicine(article: MedicineEntity)

    @Delete
    fun deleteMedicine(article: MedicineEntity)
}
