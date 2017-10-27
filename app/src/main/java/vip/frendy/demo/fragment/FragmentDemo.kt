package vip.frendy.demo.fragment

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.fragment_demo.*
import vip.frendy.demo.R
import vip.frendy.demo.net.monitorHttp
import vip.frendy.extension.base.fragment.BaseFragment
import vip.frendy.extension.monitor.Monitor
import vip.frendy.khttp.Callback

/**
 * Created by frendy on 2017/10/12.
 */
class FragmentDemo(layoutId: Int): BaseFragment(layoutId) {
    val DEFAULT_URL = "http://frendy.vip/"

    companion object {
        fun getInstance(args: Bundle? = null): FragmentDemo {
            val fragment = FragmentDemo(R.layout.fragment_demo)
            if(args != null) fragment.arguments = args
            return fragment
        }
    }

    override fun initData(args: Bundle?) {
        getNewsList("0", "0")
    }

    override fun initView() {
        webView?.loadUrl(DEFAULT_URL)
        webView?.setPageListener({ url ->
            Monitor.getInstance().onApiStart(url!!)
        }, { url ->
            Monitor.getInstance().onApiEnd(url!!)
        })
    }

    fun getNewsList(uid: String, cid: String) {
        val APP_ID = "5225"
        val APP_KEY = "9b836682f204ac0503980acd48b4df21"
        val url = "http://api.myxianwen.cn/1/news/getlist?app_id=${APP_ID}&app_key=${APP_KEY}&uid=${uid}&type_id=${cid}&equip_type=0&updown=0&version=3.6.2"

        monitorHttp(url, "get", object : Callback<String> {
            override fun onSuccess(data: String) {
                Log.i("", data)
            }
            override fun onFail(error: String?) {
                Log.i("", error)
            }
        })
    }
}