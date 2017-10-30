package vip.frendy.extension.reporter

import org.jetbrains.anko.doAsync
import vip.frendy.extension.ext.postDelayedToUI
import vip.frendy.extension.reporter.interfaces.IReporter

/**
 * Created by frendy on 2017/10/30.
 */
class Reporter {

    companion object {
        val STRATEGY_DEFAULT = 0

        private var reporter: Reporter? = null

        fun getInstance(): Reporter {
            if(reporter == null) reporter = Reporter()
            return reporter!!
        }
    }

    private var enable: Boolean = false
    private var iReporter: IReporter? = null

    private var mStrategy: Int = STRATEGY_DEFAULT
    private val STRATEGY_DEFAULT_INTERVAL = 15 * 60 * 1000L //15min

    fun init(iReporter: IReporter, strategy: Int = STRATEGY_DEFAULT) {
        this.mStrategy = strategy
        this.iReporter = iReporter

        _upload()
    }

    private fun _upload() {
        if(!enable) return

        upload()

        when(mStrategy) {
            STRATEGY_DEFAULT -> {
                postDelayedToUI({ _upload() }, STRATEGY_DEFAULT_INTERVAL)
            }
        }
    }

    fun setEnable(enable: Boolean) {
        this.enable = enable
    }

    fun upload() = doAsync {
        if(enable) iReporter?.upload()
    }

    fun fileUpload(file: String) {
        if(enable) iReporter?.fileUpload(file)
    }
}