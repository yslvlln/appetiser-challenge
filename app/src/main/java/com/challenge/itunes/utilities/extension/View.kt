package com.challenge.itunes.utilities.extension

import android.os.SystemClock
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.transition.Slide
import androidx.transition.TransitionManager


fun View.setSafeOnClickListener(debounceTime: Long = 500L, action: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) {
                return
            } else {
                action()
            }
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.slideDown(parent: ViewGroup) {
    val transition = Slide(Gravity.BOTTOM)
    transition.duration = 600
    transition.addTarget(this)

    TransitionManager.beginDelayedTransition(parent, transition)
    this.visibility = if (this.isVisible) View.VISIBLE else View.GONE
}