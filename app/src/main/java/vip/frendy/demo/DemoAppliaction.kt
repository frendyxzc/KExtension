package vip.frendy.demo

import android.app.Application
import android.util.Log
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
            override fun onCreate(localClassName: String) {
                Log.i("Monitor", "** onCreate : ${localClassName}")
            }
            override fun onResume(localClassName: String) {
                Log.i("Monitor", "** onResume : ${localClassName}")
            }
            override fun onPause(localClassName: String) {
                Log.i("Monitor", "** onPause : ${localClassName}")
            }
            override fun onDestroy(localClassName: String) {
                Log.i("Monitor", "** onDestroy : ${localClassName}")
            }
        })
    }
}