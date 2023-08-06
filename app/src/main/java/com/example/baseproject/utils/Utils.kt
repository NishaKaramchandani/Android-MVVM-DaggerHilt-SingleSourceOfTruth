package com.example.baseproject.utils

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.launchUntilPaused(lifecycleOwner: LifecycleOwner, block: suspend CoroutineScope.() -> Unit){
    val job = launch(block = block)
    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onPause(owner: LifecycleOwner) {
            job.cancel()
            lifecycleOwner.lifecycle.removeObserver(this)
        }
    })
}