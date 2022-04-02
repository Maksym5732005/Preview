package com.preview.feature.market.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.preview.base.uicomponent.dpToPx
import com.preview.base.uicomponent.startSkeletonAnimation
import com.preview.base.uicomponent.stopSkeletonAnimation
import com.preview.databinding.MarketItemDataLoadingViewBinding as Binding

private const val VERTICAL_PADDING_DP = 8

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MarketItemDataLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: Binding = Binding.inflate(LayoutInflater.from(context), this)

    init {
        val verticalPadding = dpToPx(VERTICAL_PADDING_DP)
        updatePadding(top = verticalPadding, bottom = verticalPadding)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startLoading()
    }

    @OnViewRecycled
    fun stopLoading() = with(binding) {
        textTime.stopSkeletonAnimation()
        textName.stopSkeletonAnimation()
        textAsk.stopSkeletonAnimation()
        textBid.stopSkeletonAnimation()
        textChange.stopSkeletonAnimation()
    }

    private fun startLoading() = with(binding) {
        textTime.startSkeletonAnimation()
        textName.startSkeletonAnimation()
        textAsk.startSkeletonAnimation()
        textBid.startSkeletonAnimation()
        textChange.startSkeletonAnimation()
    }
}