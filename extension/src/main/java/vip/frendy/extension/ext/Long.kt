package vip.frendy.extension.ext

import java.text.SimpleDateFormat

/**
 * Created by frendy on 2017/10/27.
 */
fun Long.toDate(): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return simpleDateFormat.format(this)
}