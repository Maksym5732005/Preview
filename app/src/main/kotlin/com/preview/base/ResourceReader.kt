package com.preview.base

import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import java.io.InputStream

interface ResourceReader {
    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg args: Any): String
    fun getQuantityString(@PluralsRes pluralResId: Int, quantity: Int, vararg formatArgs: Any): String
    fun getStringArray(@ArrayRes resId: Int): Array<String>

    @ColorInt
    fun getColor(@ColorRes resId: Int): Int

    @ColorInt
    fun getColorFromAttr(@AttrRes resId: Int): Int?

    fun getDrawable(@DrawableRes resId: Int): Drawable?

    fun getAsset(path: String): InputStream

    fun getDimension(@DimenRes resId: Int): Float
}
