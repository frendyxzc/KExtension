package com.iimedia.appbase.extension

import android.view.View
import android.view.animation.AnimationUtils

/**
 * Created by frendy on 2017/8/4.
 */
fun View.doAnimation(id: Int) {
    val anim = AnimationUtils.loadAnimation(context, id)
    startAnimation(anim)
}