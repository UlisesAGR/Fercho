/*
 * DetailProvider.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.data.provider

import android.content.Context
import com.example.clinicaldecisions.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DetailProvider @Inject constructor(
    @ApplicationContext appContext: Context,
) {
    private val resource = appContext.resources

    fun getListSuccessfulLabel(): String =
        resource.getString(R.string.error_get_medicine_list)
}