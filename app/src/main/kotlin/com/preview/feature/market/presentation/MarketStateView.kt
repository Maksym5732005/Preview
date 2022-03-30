package com.preview.feature.market.presentation

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.preview.R
import com.preview.base.extensions.getColor
import com.preview.feature.market.presentation.model.MarketInfoItemUiState
import com.preview.databinding.MarketStateItemViewBinding as Binding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MarketStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: Binding = Binding.inflate(LayoutInflater.from(context), this)

    init {
        background = ColorDrawable(getColor(R.color.bg_blue_200))
    }

    @ModelProp
    fun bind(entity: MarketInfoItemUiState?) = with(binding) {
        entity ?: return@with
        textDateTime.text = entity.dateTime
        textOpenState.text = entity.openText
        textWorkingTime.text = entity.workingTime
    }
}