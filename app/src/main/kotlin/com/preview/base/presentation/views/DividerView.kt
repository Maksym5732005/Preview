package com.preview.base.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.airbnb.epoxy.ModelView
import com.preview.R
import com.preview.base.extensions.getColor

private const val DIVIDER_HEIGHT_DP = 1

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class DividerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    init {
        setBackgroundColor(getColor(R.color.gray))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, dpToPx(DIVIDER_HEIGHT_DP))
    }
}