package vip.frendy.demo

import android.app.Application
import android.util.Log
import android.view.View
import vip.frendy.extension.base.BaseActivity
import vip.frendy.extension.monitor.Monitor
import vip.frendy.extension.monitor.interfaces.IActivity
import vip.frendy.extension.monitor.interfaces.IApi
import vip.frendy.extension.monitor.interfaces.IViewClick

/**
 * Created by frendy on 2017/10/25.
 */
class DemoAppliaction: Application() {

    val iActivity = object : IActivity {
        override fun onCreate(activity: BaseActivity) {
            Log.i("Monitor", "** onCreate : ${activity.localClassName}")
        }
        override fun onResume(activity: BaseActivity) {
            Log.i("Monitor", "** onResume : ${activity.localClassName}")
        }
        override fun onPause(activity: BaseActivity) {
            Log.i("Monitor", "** onPause : ${activity.localClassName}")
        }
        override fun onDestroy(activity: BaseActivity) {
            Log.i("Monitor", "** onDestroy : ${activity.localClassName}")
        }
    }

    val iViewClick = object : IViewClick {
        override fun onViewClick(v: View?) {
            Log.i("Monitor", "** onViewClick : ${v?.toString()}")
        }
    }

    val mStartTimeMap = hashMapOf<String, Long>()
    val iApi = object : IApi {
        override fun onStart(tag: String) {
            mStartTimeMap.put(tag, System.currentTimeMillis())
        }
        override fun onEnd(tag: String, success: Boolean, err: String) {
            if(mStartTimeMap.containsKey(tag)) {
                val cost = System.currentTimeMillis() - mStartTimeMap.get(tag)!!
                mStartTimeMap.remove(tag)

                Log.i("Monitor", "** api - ${tag} - ${success} - ${err} : ${cost}ms")
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        Monitor.getInstance().setEnable(true)
        Monitor.getInstance().init(iActivity, iViewClick, iApi)
    }
}