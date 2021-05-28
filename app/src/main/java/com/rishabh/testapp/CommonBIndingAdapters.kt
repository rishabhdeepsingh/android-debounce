package com.rishabh.testapp

import android.view.View
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

fun <T> debounce(
    delayMillis: Long = 1000L,
    scope: CoroutineScope,
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (debounceJob == null) {
            debounceJob = scope.launch {
                action(param)
                delay(delayMillis)
                debounceJob = null
            }
        }
    }
}

fun setDebounceListener(view: View, scope: CoroutineScope? = null, onClickListener: () -> Unit) {
    val coroutineScope = scope ?: (ViewTreeLifecycleOwner.get(view)?.lifecycleScope ?: MainScope())
    val clickWithDebounce: (View) -> Unit = debounce(scope = coroutineScope) {
        onClickListener.invoke()
    }
    view.setOnClickListener(clickWithDebounce)
}

