package vip.frendy.demo.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import vip.frendy.demo.R
import vip.frendy.demo.adapter.ListAdapter
import vip.frendy.extension.base.fragment.BaseFragment


/**
 * Created by frendy on 2017/10/17.
 */
class FragmentList(layoutId: Int): BaseFragment(layoutId) {
    val mList = arrayListOf<String>()
    var mIndex = 0
    val mStep = 20

    companion object {
        fun getInstance(args: Bundle? = null): FragmentList {
            val fragment = FragmentList(R.layout.fragment_list)
            if(args != null) fragment.arguments = args
            return fragment
        }
    }

    override fun initData(args: Bundle?) {
        loadData(false)
    }

    override fun initView() {
        refreshLayout.setOnRefreshListener { refreshlayout ->
            refreshData()
        }
        refreshLayout.setOnLoadmoreListener { refreshlayout ->
            loadData()
        }

        list.layoutManager = LinearLayoutManager(activity)
        list.adapter = ListAdapter(mList, {
            activity.toast(it)
        })
    }

    fun loadData(sleep: Boolean = true) = doAsync {
        val data = ArrayList<String>()
        val target = mIndex + mStep
        while(mIndex < target) data.add("TEXT --- ${mIndex++}")

        if(sleep) Thread.sleep(1000) // test

        mList.addAll(data)
        uiThread {
            list?.adapter?.notifyDataSetChanged()
            refreshLayout?.finishLoadmore()
        }
    }

    fun refreshData() = doAsync {
        val data = ArrayList<String>()
        val target = mIndex + mStep
        while(mIndex < target) data.add("TEXT --- ${mIndex++}")

        Thread.sleep(1000) // test

        mList.addAll(0, data)
        uiThread {
            list?.adapter?.notifyDataSetChanged()
            refreshLayout?.finishRefresh()
        }
    }
}