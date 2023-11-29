/*
 * DetailActivity.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.navigation.navArgs
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.databinding.ActivityDetailBinding
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.ui.detail.viewModel.DetailViewModel
import com.example.clinicaldecisions.utils.DetailEvents
import com.example.clinicaldecisions.utils.collect
import com.example.clinicaldecisions.utils.gone
import com.example.clinicaldecisions.utils.load
import com.example.clinicaldecisions.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val detailViewModel: DetailViewModel by viewModels()
    private val detailArgs: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        detailViewModel.getMedicine(detailArgs.name)
        setListeners()
        setFlows()
    }

    private fun setListeners() = with(binding) {
        detailToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setFlows() {
        collect(detailViewModel.detailState) { state ->
            loaderState(state.loading)
            setMedicine(state.detailEvents, state.data)
        }
    }

    private fun loaderState(loading: Boolean) = with(binding) {
        if (loading) {
            progressBar.show()
            dataView.gone()
        } else {
            progressBar.gone()
            dataView.show()
        }
    }

    private fun setMedicine(
        detailEvents: DetailEvents,
        medicine: MedicineModel,
    ) = with(binding) {
        if (detailEvents == DetailEvents.SUCCESS) {
            medicine.apply {
                coverImage.load(
                    uri = image.toUri(),
                    loadImage = R.drawable.ic_blur,
                    errorImage = R.drawable.ic_error,
                )
                nameTextView.text = name
                descriptionTextView.text = description
            }
        }
    }
}