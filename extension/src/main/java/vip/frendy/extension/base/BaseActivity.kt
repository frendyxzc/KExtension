package vip.frendy.extension.base

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import vip.frendy.extension.monitor.Monitor

/**
 * Created by frendy on 2017/10/11.
 */
abstract class BaseActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Monitor.getInstance().onActivityCreated(localClassName)
    }

    override fun onResume() {
        super.onResume()
        Monitor.getInstance().onActivityResume(localClassName)
    }

    override fun onPause() {
        super.onPause()
        Monitor.getInstance().onActivityPause(localClassName)
    }

    override fun onDestroy() {
        super.onDestroy()
        Monitor.getInstance().onActivityDestroy(localClassName)
    }
}