package vip.frendy.demo

import android.app.Application
import android.util.Log
import android.view.View
import vip.frendy.demo.model.Info
import vip.frendy.extension.base.BaseActivity
import vip.frendy.extension.collector.Collector
import vip.frendy.extension.collector.interfaces.ICollector
import vip.frendy.extension.ext.toDate
import vip.frendy.extension.monitor.Monitor
import vip.frendy.extension.monitor.interfaces.IActivity
import vip.frendy.extension.monitor.interfaces.IApi
import vip.frendy.extension.monitor.interfaces.ICrash
import vip.frendy.extension.monitor.interfaces.IViewClick
import java.io.File


/**
 * Created by frendy on 2017/10/25.
 */
class DemoAppliaction: Application() {

    /**
     * Monitor Observer
     */
    val iActivity = object : IActivity {
        override fun onCreate(activity: BaseActivity) {
            Log.i("Monitor", "** onCreate : ${activity.localClassName}")

            Collector.getInstance().collect(HashMap<String, String>().apply {
                put("onCreate", activity.localClassName)
            })
        }
        override fun onResume(activity: BaseActivity) {
            Log.i("Monitor", "** onResume : ${activity.localClassName}")

            Collector.getInstance().collect(HashMap<String, String>().apply {
                put("onResume", activity.localClassName)
            })
        }
        override fun onPause(activity: BaseActivity) {
            Log.i("Monitor", "** onPause : ${activity.localClassName}")

            Collector.getInstance().collect(HashMap<String, String>().apply {
                put("onPause", activity.localClassName)
            })
        }
        override fun onDestroy(activity: BaseActivity) {
            Log.i("Monitor", "** onDestroy : ${activity.localClassName}")

            Collector.getInstance().collect(HashMap<String, String>().apply {
                put("onDestroy", activity.localClassName)
            })
        }
    }

    val iViewClick = object : IViewClick {
        override fun onViewClick(v: View?) {
            Log.i("Monitor", "** onViewClick : ${v?.toString()}")

            Collector.getInstance().collect(HashMap<String, String>().apply {
                put("onViewClick", "${v?.toString()}")
            })
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

                Collector.getInstance().collect(HashMap<String, String>().apply {
                    put("Api", "${tag} - ${success} - ${err} : ${cost}ms")
                })
            }
        }
    }

    val iCrash = object : ICrash {
        override fun onCrash(info: String) {
            Log.i("Monitor", "** onCrash : ${info}")

            Collector.getInstance().collect(HashMap<String, String>().apply {
                put("onCrash", info)
            })
        }
        override fun onCrashFileUpload(file: String, filename: String) {
            Log.i("Monitor", "** onCrashFileUpload : ${file}")

            //Todo: impl function to upload file here
            File(file).delete()
        }
    }

    /**
     * Collector Observer
     */
    val iCollector = object : ICollector {
        override fun collect(info: HashMap<String, String>, timestamp: Long) {
            Log.i("Collector", "** COLLECTOR - ${timestamp.toDate()} : ${info}")

            Info.save(this@DemoAppliaction, Info(info, timestamp))
        }
    }

    override fun onCreate() {
        super.onCreate()

        // step 1, set collector enable
        Collector.getInstance().setEnable(true)
        // step 2, init collector
        Collector.getInstance().init(iCollector)

        // step 1, set monitor enable
        Monitor.getInstance().setEnable(true)
        // step 2, init monitor
        Monitor.getInstance().init(
                this.applicationContext,
                iActivity, iViewClick, iApi, iCrash)
    }
}