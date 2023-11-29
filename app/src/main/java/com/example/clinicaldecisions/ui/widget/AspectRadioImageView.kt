package com.example.clinicaldecisions.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.clinicaldecisions.R

@SuppressLint("CustomViewStyleable")
class AspectRadioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    private var radio: Float = 1f

    init {
        attrs?.let {
            val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRadioImageView)
            with(a) {
                radio = a.getFloat(R.styleable.AspectRadioImageView_ratio, 1f)
                recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var width = measuredWidth
        var height = measuredHeight

        if (width == 0 && height == 0) {
            return
        }

        if (width > 0) {
            height = (width * radio).toInt()
        } else if (height > 0) {
            width = (height / radio).toInt()
        }

        setMeasuredDimension(width, height)
    }
}