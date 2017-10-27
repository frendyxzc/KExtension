package vip.frendy.demo.net

import vip.frendy.extension.monitor.Monitor
import vip.frendy.khttp.Callback
import vip.frendy.khttp.http

/**
 * Created by frendy on 2017/10/27.
 */
fun monitorHttp(_url: String, _method: String, callback: Callback<String>) {
    http {
        url = _url
        method = _method
        onExecute {
            Monitor.getInstance().onApiStart(_url)
        }
        onSuccess { jsonStr: String ->
            Monitor.getInstance().onApiEnd(_url)
            callback.onSuccess(jsonStr)
        }
        onFail { e ->
            Monitor.getInstance().onApiEnd(_url, false, "${e.message}")
            callback.onFail(e.message)
        }
    }
}