/*
 * HoroscopeViewHolder.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.edit.adapter

import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.databinding.ItemMedicineEditBinding
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.utils.load

class EditViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMedicineEditBinding.bind(view)

    fun render(
        medicineModel: MedicineModel,
        onItemSelected: (MedicineModel) -> Unit,
        onItemDeleted: (MedicineModel) -> Unit,
    ) = with(binding) {
        medicineImageView.load(
            uri = medicineModel.image.toUri(),
            loadImage = R.drawable.ic_blur,
            errorImage = R.drawable.ic_error,
        )
        nameTextView.text = medicineModel.name
        root.setOnClickListener {
            onItemSelected(medicineModel)
        }
        deleteImageView.setOnClickListener {
            onItemDeleted(medicineModel)
        }
    }
}