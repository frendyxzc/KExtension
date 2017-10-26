package vip.frendy.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_annotations.*
import org.jetbrains.anko.toast
import vip.frendy.AnnotationsMonitor

import vip.frendy.MonitorViewClick
import vip.frendy.extension.monitor.Monitor

/**
 * Created by frendy on 2017/10/26.
 */

class AnnotationsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotations)

        button.setOnClickListener(this)
    }

    @MonitorViewClick
    override fun onClick(v: View) {
        // Todo: Can Annotations bring more convenience?
        // Monitor.getInstance().onViewClick(v)
        AnnotationsMonitor.onViewClick(null)

        when(v.id) {
            R.id.button -> toast("one one one ...")
        }
    }
}
