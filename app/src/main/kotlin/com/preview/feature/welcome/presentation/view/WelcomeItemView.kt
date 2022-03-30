package com.preview.feature.welcome.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.preview.base.uicomponent.dpToPx
import com.preview.feature.welcome.presentation.model.WelcomeItemUiState
import com.preview.databinding.WelcomeItemViewBinding as Binding

private const val PADDING_HORIZONTAL = 8

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
internal class WelcomeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: Binding = Binding.inflate(LayoutInflater.from(context), this)

    init {
        val paddingHorizontal = dpToPx(PADDING_HORIZONTAL)
        updatePadding(
            left = dpToPx(paddingHorizontal),
            right = dpToPx(paddingHorizontal)
        )
    }

    @set:CallbackProp
    var clickListener: ((WelcomeItemUiState) -> Unit)? = null

    @set:CallbackProp
    var clicker: (() -> Unit)? = null

    @ModelProp
    fun bind(entity: WelcomeItemUiState) = with(binding) {
        textTitle.text = entity.title
        entity.subtitle?.let { sub -> textSubtitle.text = sub }
        containerRipple.setOnClickListener {
            clickListener?.invoke(entity)
        }
    }
}