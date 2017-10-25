package vip.frendy.extension.monitor.interfaces

/**
 * Created by frendy on 2017/10/25.
 */
interface IActivity {

    fun onCreate(localClassName: String)
    fun onResume(localClassName: String)
    fun onPause(localClassName: String)
    fun onDestroy(localClassName: String)
}