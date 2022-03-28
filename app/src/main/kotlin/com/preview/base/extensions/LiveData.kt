package com.preview.base.extensions

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Делегат для работы с содержимым [MutableLiveData] как с полем.
 *
 * Без делегата пришлось бы писать такой код:
 * ```
 *  val liveState = MutableLiveData<IntroViewState>(initialState)
 *  var state: IntroViewState
 *      get() = liveState.requireValue()
 *      set(value) = liveState.onNext(value)
 * ```
 * С делегатом для такой же логики достаточно написать:
 * ```
 *  val liveState = MutableLiveData<IntroViewState>(initialState)
 *  var state: IntroViewState by liveState.delegate()
 * ```
 */
fun <T : Any> MutableLiveData<T>.delegate(): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = onNext(value)
        override fun getValue(thisRef: Any, property: KProperty<*>): T = requireValue()
    }
}

fun <T> MutableLiveData<T>.onNext(next: T) {
    this.value = next
}

fun <T : Any> LiveData<T>.requireValue(): T = checkNotNull(value)

/**
 * Подписка на [LiveData].
 *
 * Пример подписки на изменения состояния:
 * ```
 *  lateinit var viewModel: MyViewModel
 *
 *  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *      super.onViewCreated(view, savedInstanceState)
 *      observe(viewModel.state, ::renderState)
 *  }
 * ```
 */
inline fun <reified T, LD : LiveData<T>> Fragment.observe(liveData: LD, crossinline block: (T) -> Unit) {
    viewLifecycleOwner.observe(liveData, block)
}

/**
 * Подписка на [LiveData].
 *
 * Пример подписки на изменения состояния:
 * ```
 *  lateinit var viewModel: MyViewModel
 *
 *  override fun onCreate(savedInstanceState: Bundle?) {
 *      super.onCreate(savedInstanceState)
 *      observe(viewModel.state, ::renderState)
 *  }
 * ```
 */
inline fun <reified T, LD : LiveData<T>> LifecycleOwner.observe(liveData: LD, crossinline block: (T) -> Unit) {
    liveData.observe(this) { block(it) }
}

/** Последовательный вызов [map] и [distinctUntilChanged], в одной функции. */
inline fun <X, Y> LiveData<X>.mapDistinct(crossinline transform: (X) -> Y): LiveData<Y> {
    return map(transform).distinctUntilChanged()
}

/**
 * Создаёт [LiveData], которая отдаёт новые значения только когда результат выполнения
 * [transform] изменится. Содержимое [LiveData] не трансформируется.
 *
 * Этот метод полезно применять если нужно следить за изменением одного из полей и
 * использовать весь объект.
 * Код взят из [distinctUntilChanged], отличается тем, что добавлен [transform].
 *
 * @see distinctUntilChanged
 */
fun <X, Y> LiveData<X>.distinctUntilChanged(transform: (X) -> Y): LiveData<X> {
    val outputLiveData = MediatorLiveData<X>()
    outputLiveData.addSource(
        this,
        object : Observer<X> {
            var firstTime = true

            @SuppressLint("NullSafeMutableLiveData")
            override fun onChanged(currentValue: X) {
                val previousValue = outputLiveData.value
                val valueAppeared = firstTime || previousValue == null && currentValue != null
                if (valueAppeared ||
                    previousValue != null && transform(previousValue) != transform(currentValue)
                ) {
                    firstTime = false
                    outputLiveData.value = currentValue
                }
            }
        },
    )
    return outputLiveData
}
