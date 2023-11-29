/*
 * DetailViewModel.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.detail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicaldecisions.data.provider.DetailProvider
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.domain.repository.DetailRepository
import com.example.clinicaldecisions.utils.DetailEvents
import com.example.clinicaldecisions.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
    private val detailProvider: DetailProvider,
) : ViewModel() {

    private var _detailState = MutableStateFlow(DetailState())
    val detailState: StateFlow<DetailState> = _detailState

    fun getMedicine(name: String) = viewModelScope.launch {
        detailRepository.getMedicine(name)
            .collect { response ->
                when (response) {
                    is ResponseStatus.Loading ->
                        _detailState.update { it.copy(loading = true) }

                    is ResponseStatus.Success -> {
                        response.data?.let { medicine ->
                            _detailState.update { state ->
                                state.copy(
                                    loading = false,
                                    detailEvents = DetailEvents.SUCCESS,
                                    data = medicine,
                                )
                            }
                        }
                    }

                    is ResponseStatus.Error ->
                        _detailState.update { state ->
                            state.copy(
                                loading = false,
                                message = response.message
                                    ?: detailProvider.getListSuccessfulLabel(),
                            )
                        }
                }
            }
        resetUiState()
    }

    private fun resetUiState() {
        _detailState.update {
            it.copy(
                loading = false,
                message = "",
                detailEvents = DetailEvents.NONE,
                data = MedicineModel(
                    id = 0,
                    name = "",
                    description = "",
                    image = "",
                ),
            )
        }
    }
}