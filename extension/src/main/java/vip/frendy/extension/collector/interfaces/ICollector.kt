package vip.frendy.extension.collector.interfaces

/**
 * Created by frendy on 2017/10/27.
 */
interface ICollector {

    fun collect(info: HashMap<String, String>, timestamp: Long)
}