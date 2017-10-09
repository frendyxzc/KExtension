package com.iimedia.appbase.extension

import android.support.design.widget.AppBarLayout

/**
 * Created by frendy on 2017/8/3.
 */

fun AppBarLayout.setAppbarScrollFlag(enable: Boolean) {
    val mParams = getChildAt(0).getLayoutParams() as AppBarLayout.LayoutParams
    if (enable) {
        mParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
    } else {
        mParams.scrollFlags = 0
    }
    getChildAt(0).setLayoutParams(mParams)
}