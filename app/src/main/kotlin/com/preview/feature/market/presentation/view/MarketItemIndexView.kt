package com.preview.feature.market.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.preview.base.extensions.getColor
import com.preview.base.extensions.resolveAttribute
import com.preview.base.presentation.views.dpToPx
import com.preview.feature.market.presentation.model.IndexItemUiState
import com.preview.databinding.MarketItemIndexViewBinding as Binding

private const val VERTICAL_PADDING_DP = 8

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MarketItemIndexView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: Binding = Binding.inflate(LayoutInflater.from(context), this)

    init {
        context.resolveAttribute(android.R.attr.selectableItemBackground)?.resourceId?.let {
            setBackgroundResource(it)
        }
        val verticalPadding = dpToPx(VERTICAL_PADDING_DP)
        updatePadding(top = verticalPadding, bottom = verticalPadding)
    }

    @set:CallbackProp
    var clickListener: ((String) -> Unit)? = null

    @ModelProp
    fun bind(data: IndexItemUiState) = with(binding) {
        textTime.text = data.time
        textName.text = data.indexName
        textPrice.text = data.price
        textChange.text = data.change
        textChange.setTextColor(getColor(data.changeColorRes))
        textChangePercent.text = data.changePercent
        textChangePercent.setTextColor(getColor(data.changePercentColorRes))
        root.setOnClickListener { clickListener?.invoke(data.indexName) }
    }
}