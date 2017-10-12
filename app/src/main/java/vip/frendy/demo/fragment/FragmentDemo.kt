package vip.frendy.demo.fragment

import android.os.Bundle
import vip.frendy.demo.R
import vip.frendy.extension.base.fragment.BaseFragment

/**
 * Created by frendy on 2017/10/12.
 */
class FragmentDemo(layoutId: Int): BaseFragment(layoutId) {

    companion object {
        fun getInstance(args: Bundle? = null): FragmentDemo {
            val fragment = FragmentDemo(R.layout.fragment_demo)
            if(args != null) fragment.arguments = args
            return fragment
        }
    }

    override fun initData(args: Bundle?) {

    }

    override fun initView() {

    }
}