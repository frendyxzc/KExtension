package vip.frendy.extension.monitor

import vip.frendy.extension.base.BaseActivity
import vip.frendy.extension.monitor.interfaces.IActivity

/**
 * Created by frendy on 2017/10/25.
 */
class Monitor {

    companion object {
        private var monitor: Monitor? = null

        fun getInstance(): Monitor {
            if(monitor == null) monitor = Monitor()
            return monitor!!
        }
    }

    private var enable: Boolean = false
    private var iActivity: IActivity? = null

    fun init(iActivity: IActivity) {
        this.iActivity = iActivity
    }

    fun setEnable(enable: Boolean) {
        this.enable = enable
    }

    fun onActivityCreated(activity: BaseActivity) {
        if(enable) iActivity?.onCreate(activity)
    }

    fun onActivityResume(activity: BaseActivity) {
        if(enable) iActivity?.onResume(activity)
    }

    fun onActivityPause(activity: BaseActivity) {
        if(enable) iActivity?.onPause(activity)
    }

    fun onActivityDestroy(activity: BaseActivity) {
        if(enable) iActivity?.onDestroy(activity)
    }
}