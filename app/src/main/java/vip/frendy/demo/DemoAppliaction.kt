package vip.frendy.demo

import android.app.Application
import android.util.Log
import vip.frendy.extension.base.BaseActivity
import vip.frendy.extension.monitor.Monitor
import vip.frendy.extension.monitor.interfaces.IActivity

/**
 * Created by frendy on 2017/10/25.
 */
class DemoAppliaction: Application() {

    override fun onCreate() {
        super.onCreate()

        Monitor.getInstance().setEnable(true)
        Monitor.getInstance().init(object : IActivity {
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
        })
    }
}