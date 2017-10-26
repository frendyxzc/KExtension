package vip.frendy.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import vip.frendy.extension.base.BaseActivity

/**
 * Created by frendy on 2017/10/9.
 */
class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logo.setOnClickListener(this)
        swipeBackActivity.setOnClickListener(this)
        fragmentActivity.setOnClickListener(this)
        swipeBackFragmentActivity.setOnClickListener(this)
        annotationsActivity.setOnClickListener(this)
    }

    override fun onViewClick(v: View?) {
        when(v?.id) {
            R.id.swipeBackActivity -> startActivity<SwipeBackActivity>()
            R.id.fragmentActivity -> startActivity<FragmentActivity>()
            R.id.swipeBackFragmentActivity -> startActivity<SwipeBackFragmentActivity>()
            R.id.annotationsActivity -> startActivity<AnnotationsActivity>()
        }
    }
}