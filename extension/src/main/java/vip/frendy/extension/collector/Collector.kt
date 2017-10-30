package vip.frendy.extension.collector

import org.jetbrains.anko.doAsync
import vip.frendy.extension.collector.interfaces.ICollector

/**
 * Created by frendy on 2017/10/27.
 */
class Collector {

    companion object {
        private var collector: Collector? = null

        fun getInstance(): Collector {
            if(collector == null) collector = Collector()
            return collector!!
        }
    }

    private var enable: Boolean = false
    private var iCollector: ICollector? = null

    fun init(iCollector: ICollector) {
        this.iCollector = iCollector
    }

    fun setEnable(enable: Boolean) {
        this.enable = enable
    }

    fun collect(info: HashMap<String, String>, timestamp: Long = System.currentTimeMillis()) = doAsync {
        if(enable) iCollector?.collect(info, timestamp)
    }
}