package vip.frendy.extension.monitor

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

    fun onActivityCreated(localClassName: String) {
        if(enable) iActivity?.onCreate(localClassName)
    }

    fun onActivityResume(localClassName: String) {
        if(enable) iActivity?.onResume(localClassName)
    }

    fun onActivityPause(localClassName: String) {
        if(enable) iActivity?.onPause(localClassName)
    }

    fun onActivityDestroy(localClassName: String) {
        if(enable) iActivity?.onDestroy(localClassName)
    }
}