package com.iimedia.appbase.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * Created by frendy on 2017/9/27.
 */

fun Activity.gotoBrowser(url: String) {
    val intent = Intent()
    intent.setAction("android.intent.action.VIEW");
    val contentUrl = Uri.parse(url)
    intent.setData(contentUrl);
    startActivity(intent);
}