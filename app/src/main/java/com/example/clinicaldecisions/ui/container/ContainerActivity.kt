/*
 * ContainerActivity.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.container

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.databinding.ActivityContainerBinding
import com.example.clinicaldecisions.ui.container.viewModel.ContainerViewModel
import com.example.clinicaldecisions.utils.FormEvents
import com.example.clinicaldecisions.utils.collect
import com.example.clinicaldecisions.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityContainerBinding.inflate(layoutInflater)
    }

    private val containerViewModel: ContainerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        initNavigation()
        setFlows()
    }

    private fun setFlows() {
        collect(containerViewModel.containerState) { state ->
            toast(state.message)
            setFormEvents(state.formEvent)
        }
    }

    private fun setFormEvents(state: FormEvents) {
        when (state) {
            FormEvents.NONE -> {}
            FormEvents.EMPTY_IMAGE -> toast(getString(R.string.empty_image))
            FormEvents.EMPTY_NAME -> toast(getString(R.string.empty_name))
            FormEvents.EMPTY_DESCRIPTION -> toast(getString(R.string.empty_description))
            FormEvents.COMPLETE -> {}
        }
    }

    private fun initNavigation() {
        binding.bottomNavigationView.setupWithNavController(
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        )
    }
}