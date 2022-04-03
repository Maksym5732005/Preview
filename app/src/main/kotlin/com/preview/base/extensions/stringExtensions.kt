package com.preview.base.extensions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * For strings from Manifest.permission.
 */
fun Array<out String>.hasPermission(context: Context): Boolean {
    return this.all { it.hasPermission(context) }
}

/**
 * For strings from Manifest.permission.
 */
fun String.hasPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, this) == PackageManager.PERMISSION_GRANTED
}