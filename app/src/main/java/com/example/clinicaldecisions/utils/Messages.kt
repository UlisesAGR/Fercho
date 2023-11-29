/*
 * Messages.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.utils

import android.content.Context
import android.widget.Toast
import com.example.clinicaldecisions.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.toast(message: String?) {
    if (!message.isNullOrEmpty()) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun Context.materialDialog(
    title: String,
    message: String,
    action: () -> Unit,
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        .setPositiveButton(resources.getString(R.string.accept)) { dialog, _ ->
            action.invoke()
            dialog.dismiss()
        }
        .show()
}