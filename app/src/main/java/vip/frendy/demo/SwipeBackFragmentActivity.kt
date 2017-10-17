package vip.frendy.demo

import android.os.Bundle
import vip.frendy.demo.fragment.FragmentList
import vip.frendy.extension.base.BaseSwipeBackFragmentActivity

/**
 * Created by frendy on 2017/10/12.
 */
class SwipeBackFragmentActivity: BaseSwipeBackFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDefaultFragment(FragmentList.getInstance())
    }
}