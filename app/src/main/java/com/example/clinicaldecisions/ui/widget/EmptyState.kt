/*
 * EmptyState.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.clinicaldecisions.databinding.EmptyStateBinding
import com.example.clinicaldecisions.domain.model.EmptyStateModel
import com.example.clinicaldecisions.utils.Constants.ANIMATION_ALFA_HIDDEN
import com.example.clinicaldecisions.utils.Constants.ANIMATION_ALFA_VISIBLE
import com.example.clinicaldecisions.utils.show

class EmptyState @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        EmptyStateBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun show(emptyStateModel: EmptyStateModel) {
        emptyStateModel.apply {
            showEmptyState()
            setupImage(image)
            setupTitle(title)
            setupSubTitle(subTitle)
        }
    }

    private fun setupImage(image: Int) = with(binding) {
        imageView.setImageResource(image)
    }

    private fun setupTitle(title: String?) {
        with(binding.titleTxt) {
            if (!title.isNullOrEmpty()) {
                text = title
                show()
            }
        }
    }

    private fun setupSubTitle(subTitle: String?) {
        with(binding.subTitleTxt) {
            if (!subTitle.isNullOrEmpty()) {
                text = subTitle
                show()
            }
        }
    }

    private fun showEmptyState() {
        clearAnimation()
        animBannerIn()
    }

    private fun animBannerIn() {
        with(binding.containerLayout) {
            alpha = ANIMATION_ALFA_HIDDEN
            show()
            animate()
                .alpha(ANIMATION_ALFA_VISIBLE)
                .setDuration(resources.getInteger(android.R.integer.config_mediumAnimTime).toLong())
                .setListener(null)
        }
    }
}