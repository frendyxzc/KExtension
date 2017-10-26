package vip.frendy.extension.base

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import vip.frendy.extension.monitor.Monitor

/**
 * Created by frendy on 2017/10/11.
 */
abstract class BaseActivity: FragmentActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Monitor.getInstance().onActivityCreated(this)
    }

    override fun onResume() {
        super.onResume()
        Monitor.getInstance().onActivityResume(this)
    }

    override fun onPause() {
        super.onPause()
        Monitor.getInstance().onActivityPause(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Monitor.getInstance().onActivityDestroy(this)
    }

    override fun onClick(v: View?) {
        Monitor.getInstance().onViewClick(v)
        onViewClick(v)
    }

    abstract fun onViewClick(v: View?)
}