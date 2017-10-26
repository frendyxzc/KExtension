package vip.frendy.extension.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import vip.frendy.extension.R

/**
 * Created by frendy on 2017/10/11.
 */
abstract class BaseFragmentActivity: BaseActivity() {
    protected var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_fragment)
    }

    protected fun setDefaultFragment(fragment: Fragment) {
        // 开启Fragment事务
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 使用fragment的布局替代frame_layout的控件并提交事务
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit()
        currentFragment = fragment
    }

    protected fun switchFragment(fragment: Fragment) {
        if (fragment !== currentFragment) {
            if (!fragment.isAdded) {
                supportFragmentManager.beginTransaction().hide(currentFragment)
                        .add(R.id.frame_layout, fragment).commit()
            } else {
                supportFragmentManager.beginTransaction().hide(currentFragment)
                        .show(fragment).commit()
            }
            currentFragment = fragment
        }
    }

    override fun onResume() {
        super.onResume()
        currentFragment?.onResume()
    }

    override fun onPause() {
        super.onPause()
        currentFragment?.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        currentFragment?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}