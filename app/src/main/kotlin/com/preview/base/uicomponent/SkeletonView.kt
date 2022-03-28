package com.preview.base.uicomponent

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.updatePaddingRelative
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import kotlin.math.roundToInt

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class SkeletonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {

    @ModelProp
    override fun setImageResource(@DrawableRes resId: Int) {
        super.setImageResource(resId)
        startLoading()
    }

    @ModelProp
    fun setHorizontalPadding(@Dimension sizeDp: Int?) {
        if (sizeDp == null) return

        val paddingPx = dpToPx(sizeDp)
        updatePaddingRelative(start = paddingPx, end = paddingPx)
    }

    @OnViewRecycled
    fun stopLoading() = clearAnimation()

    fun startLoading() = startSkeletonAnimation()
}

fun View.dpToPx(value: Number): Int = context.dpToPx(value)

fun Context.dpToPx(value: Number): Int = dpToPxPrecisely(value).roundToInt()

fun Context.dpToPxPrecisely(value: Number): Float = value.toFloat() * resources.displayMetrics.density