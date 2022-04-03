@file:Suppress("unused")

package com.preview.base.presentation.views

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.preview.R

private var savedAnimation: Animation? = null

fun View.startSkeletonAnimation() {
    val animation = savedAnimation ?: AnimationUtils.loadAnimation(context, R.anim.blink).also {
        savedAnimation = it
    }
    startAnimation(animation)
}

fun View.stopSkeletonAnimation() {
    clearAnimation()
}

fun View.updateSkeletonAnimation(isRunning: Boolean) {
    if (isRunning) startSkeletonAnimation() else stopSkeletonAnimation()
}
