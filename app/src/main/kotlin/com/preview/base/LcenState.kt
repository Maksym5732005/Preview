package com.preview.base

sealed class LcenState<out T : Any> {
    object Loading : LcenState<Nothing>()
    data class Content<C : Any>(val value: C) : LcenState<C>()
    data class Error(val value: Throwable) : LcenState<Nothing>() {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Error

            // Cause Throwable does not override equals. Otherwise you will always get false.
            if (value.javaClass != other.value.javaClass) return false

            return true
        }

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + value.hashCode()
            return result
        }
    }

    fun asContent(): T {
        return (this as Content<T>).value
    }

    fun asError(): Throwable {
        return (this as Error).value
    }

    fun asContentOrNull(): T? {
        return (this as? Content<T>)?.value
    }

    fun asErrorOrNull(): Throwable? {
        return (this as? Error)?.value
    }

    override fun toString(): String = javaClass.simpleName

    // Default implementation equals and hashCode for None and Loading.
    // Used by Epoxy with @ModelProp annotation.
    override fun equals(other: Any?): Boolean = this === other
    override fun hashCode(): Int = javaClass.hashCode()
}
