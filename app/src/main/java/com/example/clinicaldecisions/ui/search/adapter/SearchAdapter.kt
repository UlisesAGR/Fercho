/*
 * SearchAdapter.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.search.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.utils.inflate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("NotifyDataSetChanged")
class SearchAdapter(
    private var medicineList: MutableList<MedicineModel> = mutableListOf(),
    private var filterMedicineList: MutableList<MedicineModel> = mutableListOf(),
    private val onItemSelected: (MedicineModel) -> Unit,
) : RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(parent.inflate(R.layout.item_medicine, attachToRoot = false))

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.render(filterMedicineList[position], onItemSelected)
    }

    override fun getItemCount(): Int =
        filterMedicineList.size

    suspend fun addList(list: List<MedicineModel>) =
        withContext(Dispatchers.Main) {
            medicineList = list.toMutableList()
            filterMedicineList = medicineList
            notifyDataSetChanged()
        }

    suspend fun filterListByName(name: String) =
        withContext(Dispatchers.Main) {
            val newList = medicineList.filter { medicine ->
                medicine.name.lowercase().contains(name.lowercase())
            }
            updateList(newList)
        }

    private suspend fun updateList(items: List<MedicineModel>) =
        withContext(Dispatchers.Main) {
            filterMedicineList = items.toMutableList()
            notifyDataSetChanged()
        }

}