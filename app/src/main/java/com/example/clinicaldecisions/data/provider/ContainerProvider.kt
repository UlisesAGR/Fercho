/*
 * ContainerProvider.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.data.provider

import android.content.Context
import com.example.clinicaldecisions.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContainerProvider @Inject constructor(
    @ApplicationContext appContext: Context,
) {
    private val resource = appContext.resources

    fun getCreatedSuccessfulLabel(): String =
        resource.getString(R.string.medicine_created_successful)

    fun getErrorCreatedLabel(): String =
        resource.getString(R.string.error_create_the_medicine)

    fun getUpdatedSuccessfulLabel(): String =
        resource.getString(R.string.medicine_updated_successful)

    fun getErrorUpdatedLabel(): String =
        resource.getString(R.string.error_update_the_medicine)

    fun getDeletedSuccessfulLabel(): String =
        resource.getString(R.string.medicine_deleted_successful)

    fun getErrorDeletedLabel(): String =
        resource.getString(R.string.error_delete_the_medicine)
}