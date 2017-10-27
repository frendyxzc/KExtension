package vip.frendy.extension.monitor

import android.view.View
import vip.frendy.extension.base.BaseActivity
import vip.frendy.extension.monitor.interfaces.IActivity
import vip.frendy.extension.monitor.interfaces.IApi
import vip.frendy.extension.monitor.interfaces.IViewClick

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
    private var iViewClick: IViewClick? = null
    private var iApi: IApi? = null

    fun init(iActivity: IActivity?, iViewClick: IViewClick?, iApi: IApi?) {
        this.iActivity = iActivity
        this.iViewClick = iViewClick
        this.iApi = iApi
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

    /**
     * View.OnClickListener
     */
    fun onViewClick(v: View?) {
        if(enable) iViewClick?.onViewClick(v)
    }

    /**
     * Api
     */
    fun onApiStart(tag: String) {
        if(enable) iApi?.onStart(tag)
    }

    fun onApiEnd(tag: String, success: Boolean = true, err: String = "NoError") {
        if(enable) iApi?.onEnd(tag, success, err)
    }
}