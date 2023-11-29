/*
 * SearchViewHolder.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.search.adapter

import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.databinding.ItemMedicineBinding
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.utils.load

class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMedicineBinding.bind(view)

    fun render(
        medicineModel: MedicineModel,
        onItemSelected: (MedicineModel) -> Unit,
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
    }
}