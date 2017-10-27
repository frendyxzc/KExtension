package vip.frendy.extension.monitor.interfaces

/**
 * Created by frendy on 2017/10/27.
 */
interface ICrash {

    fun onCrash(info: String)
    fun onCrashFileUpload(file: String, filename: String)
}