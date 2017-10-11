package vip.frendy.extension.ext

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by frendy on 2017/6/20.
 */

fun EditText.hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
}