package com.preview.feature.market.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.preview.databinding.MarketItemTitleViewBinding as Binding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MarketItemDataTitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: Binding = Binding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = VERTICAL
    }

    @ModelProp
    fun setTitle(@StringRes titleRes: Int) {
        binding.textTitle.text = context.getString(titleRes)
    }
}