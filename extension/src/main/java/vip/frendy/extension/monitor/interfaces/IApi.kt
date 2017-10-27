package vip.frendy.extension.monitor.interfaces

/**
 * Created by frendy on 2017/10/27.
 */
interface IApi {

    fun onStart(tag: String)
    fun onEnd(tag: String, success: Boolean, err: String)
}