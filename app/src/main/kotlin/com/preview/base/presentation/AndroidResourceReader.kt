@file:SuppressWarnings("ForbiddenImport")

package com.preview.base.presentation

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.preview.base.extensions.getColorFromAttr
import com.preview.base.extensions.getDimension
import java.io.InputStream
import javax.inject.Inject

internal class AndroidResourceReader @Inject constructor(
    private val context: Context,
) : ResourceReader {

    private val resources = context.resources

    override fun getString(resId: Int): String {
        return resources.getString(resId)
    }

    override fun getString(resId: Int, vararg args: Any): String {
        @Suppress("SpreadOperator") // Does not affect performance, needed for parameter passing
        return resources.getString(resId, *args)
    }

    override fun getQuantityString(pluralResId: Int, quantity: Int, vararg formatArgs: Any): String {
        @Suppress("SpreadOperator") // Does not affect performance, needed for parameter passing
        return resources.getQuantityString(pluralResId, quantity, *formatArgs)
    }

    override fun getStringArray(resId: Int): Array<String> {
        return resources.getStringArray(resId)
    }

    override fun getColor(resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    override fun getColorFromAttr(resId: Int): Int? {
        return context.getColorFromAttr(resId)
    }

    override fun getDrawable(resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }

    override fun getAsset(path: String): InputStream {
        return context.assets.open(path)
    }

    override fun getDimension(resId: Int): Float {
        return context.getDimension(resId)
    }
}
