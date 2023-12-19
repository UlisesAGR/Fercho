/*
 * ViewVisibility.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.utils

import android.view.View

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.setEmptyState(itemCount: Int): View {
    visibility = if (itemCount == 0) {
        View.VISIBLE
    } else
        View.GONE
    return this
}