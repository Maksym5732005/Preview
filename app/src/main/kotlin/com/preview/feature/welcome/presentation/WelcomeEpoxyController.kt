package com.preview.feature.welcome.presentation

import com.airbnb.epoxy.TypedEpoxyController
import com.preview.R
import com.preview.base.LcenState
import com.preview.base.uicomponent.skeletonView
import com.preview.base.uicomponent.space
import com.preview.feature.welcome.presentation.model.WelcomeItemUiEntity
import com.preview.feature.welcome.presentation.view.welcomeItemView

private const val SKELETON_VIEWS_COUNT = 5
private const val SKELETON_SPACE_HEIGHT_DP = 8
private const val SKELETON_HORIZONTAL_PADDING_DP = 16

class WelcomeEpoxyController(
    private val itemClickListener: (WelcomeItemUiEntity) -> Unit,
) : TypedEpoxyController<LcenState<List<WelcomeItemUiEntity>>>() {

    override fun buildModels(data: LcenState<List<WelcomeItemUiEntity>>?) {
        when (data) {
            is LcenState.Content -> buildContent(data.asContent())
            else -> buildLoading()
        }
    }

    private fun buildContent(content: List<WelcomeItemUiEntity>) {
        content.forEachIndexed { i, uiEntity ->
            if (i > 0) {
                space {
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
                space {
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