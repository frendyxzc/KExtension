package vip.frendy.extension.monitor

import android.content.Context
import android.view.View
import org.jetbrains.anko.doAsync
import vip.frendy.extension.base.BaseActivity
import vip.frendy.extension.monitor.crash.CrashHandler
import vip.frendy.extension.monitor.interfaces.IActivity
import vip.frendy.extension.monitor.interfaces.IApi
import vip.frendy.extension.monitor.interfaces.ICrash
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

    private var mContext: Context? = null

    private var enable: Boolean = false
    private var iActivity: IActivity? = null
    private var iViewClick: IViewClick? = null
    private var iApi: IApi? = null
    private var iCrash: ICrash? = null

    fun init(context: Context, iActivity: IActivity?, iViewClick: IViewClick?, iApi: IApi?, iCrash: ICrash?) {
        this.mContext = context
        this.iActivity = iActivity
        this.iViewClick = iViewClick
        this.iApi = iApi
        this.iCrash = iCrash

        if(!enable) return

        CrashHandler.getInstance().init(mContext, iCrash)
        doAsync {
            CrashHandler.getInstance().crashFileUpload()
        }
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