/*
 * LoadImage.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.load(
    url: String?,
    loadImage: Int,
    errorImage: Int,
) {
    Glide.with(this)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(loadImage)
        .error(errorImage)
        .into(this)
}

fun ImageView.load(
    uri: Uri?,
    loadImage: Int,
    errorImage: Int,
) {
    Glide.with(this)
        .load(uri)
        .transition(DrawableTransitionOptions.withCrossFade())
        .placeholder(loadImage)
        .error(errorImage)
        .into(this)
}