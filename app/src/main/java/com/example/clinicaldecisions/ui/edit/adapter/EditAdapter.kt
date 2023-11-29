/*
 * HoroscopeAdapter.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.edit.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.utils.inflate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditAdapter(
    private var medicineList: List<MedicineModel> = emptyList(),
    private val onItemSelected: (MedicineModel) -> Unit,
    private val onItemDeleted: (MedicineModel) -> Unit,
) : RecyclerView.Adapter<EditViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditViewHolder =
        EditViewHolder(parent.inflate(R.layout.item_medicine_edit, attachToRoot = false))

    override fun onBindViewHolder(holder: EditViewHolder, position: Int) {
        holder.render(medicineList[position], onItemSelected, onItemDeleted)
    }

    override fun getItemCount(): Int =
        medicineList.size

    @SuppressLint("NotifyDataSetChanged")
    suspend fun setList(list: List<MedicineModel>) =
        withContext(Dispatchers.Main) {
            medicineList = list
            notifyDataSetChanged()
        }
}