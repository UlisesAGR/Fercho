/*
 * ContainerViewModel.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.container.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicaldecisions.data.provider.ContainerProvider
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.domain.repository.ContainerRepository
import com.example.clinicaldecisions.utils.FormEvents
import com.example.clinicaldecisions.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContainerViewModel @Inject constructor(
    private val containerRepository: ContainerRepository,
    private val containerProvider: ContainerProvider,
) : ViewModel() {

    private var _containerState = MutableStateFlow(ContainerState())
    val containerState: StateFlow<ContainerState> = _containerState

    init {
        getAllMedicines()
    }

    private fun getAllMedicines() = viewModelScope.launch {
        containerRepository.getAllMedicines()
            .collect { medicines ->
                _containerState.update { state -> state.copy(list = medicines) }
            }
    }

    fun validateFormCreate(
        name: String,
        description: String,
        image: String,
    ) {
        _containerState.update {
            it.copy(
                formEvent = when {
                    name.isEmpty() -> FormEvents.EMPTY_NAME
                    description.isEmpty() -> FormEvents.EMPTY_DESCRIPTION
                    image.isEmpty() -> FormEvents.EMPTY_IMAGE
                    else -> FormEvents.COMPLETE
                }
            )
        }
        resetUiState()
    }

    fun createMedicine(medicine: MedicineModel) = viewModelScope.launch {
        containerRepository.createMedicine(medicine)
            .collect { response ->
                when (response) {
                    is ResponseStatus.Loading -> {}
                    is ResponseStatus.Success ->
                        _containerState.update { state ->
                            state.copy(
                                message = containerProvider.getCreatedSuccessfulLabel(),
                            )
                        }

                    is ResponseStatus.Error ->
                        _containerState.update { state ->
                            state.copy(
                                message = response.message
                                    ?: containerProvider.getErrorCreatedLabel(),
                            )
                        }
                }
            }
        resetUiState()
    }

    fun validateFormUpdate(
        name: String,
        description: String,
        image: String,
    ) {
        _containerState.update {
            it.copy(
                formEvent = when {
                    name.isEmpty() -> FormEvents.EMPTY_NAME
                    description.isEmpty() -> FormEvents.EMPTY_DESCRIPTION
                    image.isEmpty() -> FormEvents.EMPTY_IMAGE
                    else -> FormEvents.COMPLETE
                }
            )
        }
        resetUiState()
    }

    fun updateMedicine(article: MedicineModel) = viewModelScope.launch {
        containerRepository.updateMedicine(article)
            .collect { response ->
                when (response) {
                    is ResponseStatus.Loading -> {}
                    is ResponseStatus.Success ->
                        _containerState.update { state ->
                            state.copy(
                                message = containerProvider.getUpdatedSuccessfulLabel(),
                            )
                        }

                    is ResponseStatus.Error ->
                        _containerState.update { state ->
                            state.copy(
                                message = response.message
                                    ?: containerProvider.getErrorUpdatedLabel(),
                            )
                        }
                }
            }
        resetUiState()
    }

    fun deleteMedicine(article: MedicineModel) = viewModelScope.launch {
        containerRepository.deleteMedicine(article)
            .collect { response ->
                when (response) {
                    is ResponseStatus.Loading -> {}
                    is ResponseStatus.Success ->
                        _containerState.update { state ->
                            state.copy(
                                message = containerProvider.getDeletedSuccessfulLabel(),
                            )
                        }

                    is ResponseStatus.Error ->
                        _containerState.update { state ->
                            state.copy(
                                message = response.message
                                    ?: containerProvider.getErrorDeletedLabel(),
                            )
                        }
                }
            }
        resetUiState()
    }

    private fun resetUiState() {
        _containerState.update {
            it.copy(
                message = "",
                formEvent = FormEvents.NONE,
            )
        }
    }
}