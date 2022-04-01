package com.preview.feature.market.presentation.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.preview.R
import com.preview.base.extensions.getColor
import com.preview.base.uicomponent.startSkeletonAnimation
import com.preview.base.uicomponent.stopSkeletonAnimation
import com.preview.databinding.MarketStateItemLoadingViewBinding as Binding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MarketStateLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: Binding = Binding.inflate(LayoutInflater.from(context), this)

    init {
        background = ColorDrawable(getColor(R.color.bg_blue_200))
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startLoading()
    }

    @OnViewRecycled
    fun stopLoading() = with(binding) {
        textDateTime.stopSkeletonAnimation()
        textOpenState.stopSkeletonAnimation()
        textWorkingTime.stopSkeletonAnimation()
    }

    private fun startLoading() = with(binding) {
        textDateTime.startSkeletonAnimation()
        textOpenState.startSkeletonAnimation()
        textWorkingTime.startSkeletonAnimation()
    }
}