package vip.frendy.demo

import android.os.Bundle
import vip.frendy.demo.fragment.FragmentDemo
import vip.frendy.extension.base.BaseFragmentActivity

/**
 * Created by frendy on 2017/10/12.
 */
class FragmentActivity: BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDefaultFragment(FragmentDemo.getInstance())
    }
}