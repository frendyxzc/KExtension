package vip.frendy.demo

import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_swipeback.*
import vip.frendy.demo.model.Info
import vip.frendy.extension.base.BaseSwipeBackActivity

/**
 * Created by frendy on 2017/10/11.
 */
class SwipeBackActivity: BaseSwipeBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipeback)

        showInfo.setOnClickListener(this)
    }

    override fun onViewClick(v: View?) {
        when(v?.id) {
            R.id.showInfo -> {
                val infos = Info.queryAll(this)
                for(info in infos) {
                    Log.i("Collector", "** SHOW-INFOS : ${info.toString()}")
                }

                Info.delete(this, infos)
            }
        }
    }
}