package com.canli.oya.diceroller

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import java.util.concurrent.atomic.AtomicBoolean


class SimpleIdlingResource : IdlingResource {

    @Volatile
    private var mCallback: ResourceCallback? = null

    private val isIdleNow = AtomicBoolean(true)

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        return isIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback) {
        mCallback = callback
    }

    fun setIdleState(idle: Boolean) {
        println(idle)
        isIdleNow.set(idle)
        if (idle) {
            mCallback?.onTransitionToIdle()
        }
    }
}