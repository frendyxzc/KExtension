package vip.frendy.extension.ext

import android.app.Activity
import android.content.Context
import android.support.v7.appcompat.R
import android.support.v7.widget.SearchView
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by frendy on 2017/7/20.
 */
fun SearchView.hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
}

fun SearchView.setTextStyle(textSize: Float, hintTextColor: Int, textColor: Int) {
    //获取到TextView的控件
    val textView = findViewById<EditText>(R.id.search_src_text)
    //设置字体大小为14sp
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
    //设置字体颜色
    textView.setTextColor(context.getResources().getColor(textColor))
    //设置提示文字颜色
    textView.setHintTextColor(context.getResources().getColor(hintTextColor))
}