package vip.frendy.extension.base

import android.os.Bundle
import vip.frendy.extension.utils.swipeBack.SwipeBackLayout
import vip.frendy.extension.utils.swipeBack.Utils
import vip.frendy.extension.utils.swipeBack.app.SwipeBackActivityHelper

/**
 * Created by frendy on 2017/10/11.
 */
abstract class BaseSwipeBackActivity: BaseActivity() {
    private var mSwipeBackLayout: SwipeBackLayout? = null
    private var mHelper: SwipeBackActivityHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHelper = SwipeBackActivityHelper(this)
        mHelper?.onActivityCreate()

        mSwipeBackLayout = getSwipeBackLayout()
        mSwipeBackLayout?.setSensitivity(this, 0.3f)
        mSwipeBackLayout?.setEdgeSizePercent(0.5f)
        mSwipeBackLayout?.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
        mSwipeBackLayout?.addSwipeListener(object : SwipeBackLayout.SwipeListener {
            override fun onScrollStateChange(state: Int, scrollPercent: Float) {
                _onScrollStateChange(state, scrollPercent)
            }
            override fun onEdgeTouch(edgeFlag: Int) {
                _onEdgeTouch(edgeFlag)
            }
            override fun onScrollOverThreshold() {
                _onScrollOverThreshold()
            }
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mHelper?.onPostCreate()
    }

    fun getSwipeBackLayout(): SwipeBackLayout? {
        return mHelper?.getSwipeBackLayout()
    }

    fun setSwipeBackEnable(enable: Boolean) {
        getSwipeBackLayout()?.setEnableGesture(enable)
    }

    fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this)
        getSwipeBackLayout()?.scrollToFinishActivity()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    protected fun _onEdgeTouch(edgeFlag: Int) {

    }

    protected fun _onScrollOverThreshold() {

    }

    protected fun _onScrollStateChange(state: Int, scrollPercent: Float) {

    }
}