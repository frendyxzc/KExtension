package vip.frendy.extension.ext

import android.content.Context
import android.net.ConnectivityManager
import vip.frendy.extension.ext.Network.Companion.NETWORK_STATE_NET
import vip.frendy.extension.ext.Network.Companion.NETWORK_STATE_NULL
import vip.frendy.extension.ext.Network.Companion.NETWORK_STATE_WAP
import vip.frendy.extension.ext.Network.Companion.NETWORK_STATE_WIFI
import java.io.IOException


/**
 * Created by frendy on 2017/10/30.
 *
 * Need permissions as follow:
 *  - android.permission.INTERNET
 *  - android.permission.ACCESS_NETWORK_STATE
 */
class Network(val context: Context?) {
    companion object {
        var NETWORK_STATE_NULL = -1
        var NETWORK_STATE_WIFI = 1
        var NETWORK_STATE_WAP = 2
        var NETWORK_STATE_NET = 3
    }

    fun getNetworkState(): String {
        var state = "NoNetwork"
        when(context?.getNetworkState()) {
            NETWORK_STATE_NULL -> state = "NoNetwork"
            NETWORK_STATE_WIFI -> state = "Wifi"
            NETWORK_STATE_WAP -> state = "Wap"
            NETWORK_STATE_NET -> state = "Net"
        }
        return state
    }

    fun ping(target: String): Int {
        var status = 0
        var p: Process? = null
        try {
            p = Runtime.getRuntime().exec("ping -c 1 -w 5 " + target)
            status = p!!.waitFor()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return status
    }
}

//返回值 -1：没有网络  1：WIFI网络 2：wap网络 3：net网络
fun Context.getNetworkState(): Int {
    var netType = NETWORK_STATE_NULL
    val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connMgr.activeNetworkInfo ?: return netType

    val nType = networkInfo.type
    if (nType == ConnectivityManager.TYPE_MOBILE) {
        if (networkInfo.extraInfo == null) {
            netType = NETWORK_STATE_NET
            return netType
        }
        if (networkInfo.extraInfo.toLowerCase() == "cmnet") {
            netType = NETWORK_STATE_NET
        } else {
            netType = NETWORK_STATE_WAP
        }
    } else if (nType == ConnectivityManager.TYPE_WIFI) {
        netType = NETWORK_STATE_WIFI
    }
    return netType
}

fun Context.isWifiConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    return if (wifiNetworkInfo.isConnected) true else false
}