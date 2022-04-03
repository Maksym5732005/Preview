package com.preview.feature.welcome.presentation

import com.airbnb.epoxy.TypedEpoxyController
import com.preview.R
import com.preview.base.domain.LcenState
import com.preview.base.presentation.views.skeletonView
import com.preview.base.presentation.views.spaceView
import com.preview.feature.welcome.presentation.model.WelcomeItemUiState
import com.preview.feature.welcome.presentation.view.welcomeItemView

private const val SKELETON_VIEWS_COUNT = 2
private const val SKELETON_SPACE_HEIGHT_DP = 8
private const val SKELETON_HORIZONTAL_PADDING_DP = 16

class WelcomeEpoxyController(
    private val itemClickListener: (WelcomeItemUiState) -> Unit,
) : TypedEpoxyController<LcenState<List<WelcomeItemUiState>>>() {

    override fun buildModels(data: LcenState<List<WelcomeItemUiState>>?) {
        when (data) {
            is LcenState.Content -> buildContent(data.asContent())
            else -> buildLoading()
        }
    }

    private fun buildContent(content: List<WelcomeItemUiState>) {
        content.forEachIndexed { i, uiEntity ->
            if (i > 0) {
                spaceView {
                    id("space_$i")
                    height(SKELETON_SPACE_HEIGHT_DP)
                }
            }
            welcomeItemView {
                id("item_$i")
                bind(uiEntity)
                clickListener(this@WelcomeEpoxyController.itemClickListener::invoke)
            }
        }
    }

    private fun buildLoading() {
        repeat(SKELETON_VIEWS_COUNT) { position ->
            if (position > 0) {
                spaceView {
                    id("space_$position")
                    height(SKELETON_SPACE_HEIGHT_DP)
                }
            }
            skeletonView {
                id("welcome_loading_$position")
                imageResource(R.drawable.img_welcome_skeleton)
                horizontalPadding(SKELETON_HORIZONTAL_PADDING_DP)
            }
        }
    }
}