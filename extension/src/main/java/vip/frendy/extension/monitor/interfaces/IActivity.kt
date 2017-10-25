package vip.frendy.extension.monitor.interfaces

import vip.frendy.extension.base.BaseActivity

/**
 * Created by frendy on 2017/10/25.
 */
interface IActivity {

    fun onCreate(activity: BaseActivity)
    fun onResume(activity: BaseActivity)
    fun onPause(activity: BaseActivity)
    fun onDestroy(activity: BaseActivity)
}