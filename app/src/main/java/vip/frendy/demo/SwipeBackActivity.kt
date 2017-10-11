package vip.frendy.demo

import android.os.Bundle
import vip.frendy.extension.base.BaseSwipeBackActivity

/**
 * Created by frendy on 2017/10/11.
 */
class SwipeBackActivity: BaseSwipeBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipeback)
    }
}