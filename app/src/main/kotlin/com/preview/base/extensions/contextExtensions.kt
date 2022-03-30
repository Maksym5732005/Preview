@file:Suppress("unused")

package com.preview.base.extensions

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat

@ColorInt
fun Context.getColorCompat(@ColorRes colorResId: Int): Int = ContextCompat.getColor(this, colorResId)

@ColorInt
fun Context.getColorFromAttr(@AttrRes attrColor: Int): Int? = getColorResFromAttr(attrColor)?.let(::getColorCompat)

@ColorRes
fun Context.getColorResFromAttr(@AttrRes attrColor: Int): Int? = getResourceIdFromAttr(attrColor)

fun Context.getResourceIdFromAttr(@AttrRes attr: Int): Int? = resolveAttribute(attr)?.resourceId

fun Context.resolveAttribute(@AttrRes attributeResId: Int): TypedValue? {
    return TypedValue().takeIf { theme.resolveAttribute(attributeResId, it, true) }
}

fun Context.getDimension(@DimenRes id: Int): Float = resources.getDimension(id)