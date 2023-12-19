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
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.databinding.EmptyStateBinding
import com.example.clinicaldecisions.utils.Constants.ANIMATION_ALFA_HIDDEN
import com.example.clinicaldecisions.utils.Constants.ANIMATION_ALFA_VISIBLE
import com.example.clinicaldecisions.utils.show

class EmptyState @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        EmptyStateBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EmptyState,
            0,
            0,
        ).apply {
            try {
                showEmptyState()
                setImage(getResourceId(R.styleable.EmptyState_image, 0))
                setTitle(getString(R.styleable.EmptyState_title))
                setSubTitle(getString(R.styleable.EmptyState_subtitle))
            } finally {
                recycle()
            }
        }
    }

    private fun setImage(image: Int) = with(binding) {
        with(binding.imageView) {
            if (image != 0) {
                imageView.setImageResource(image)
                show()
            }
        }
    }

    private fun setTitle(title: String?) {
        with(binding.titleTxt) {
            if (!title.isNullOrEmpty()) {
                text = title
                show()
            }
        }
    }

    private fun setSubTitle(subTitle: String?) {
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