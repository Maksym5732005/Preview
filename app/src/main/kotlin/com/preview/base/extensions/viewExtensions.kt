package com.preview.base.extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun View.getColor(@ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)

fun View.getString(@StringRes stringRes: Int) = context.getString(stringRes)