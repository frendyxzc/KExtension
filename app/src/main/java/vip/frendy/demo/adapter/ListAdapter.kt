package vip.frendy.demo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list.view.*
import vip.frendy.demo.R

/**
 * Created by frendy on 2017/10/17.
 */
class ListAdapter(val mList: ArrayList<String>, val itemClickListener: (String) -> Unit): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int = mList.size


    class ViewHolder(view: View, val itemClickListener: (String) -> Unit): RecyclerView.ViewHolder(view) {
        fun bind(info: String) {
            with(info) {
                itemView.title.text = info
                itemView.setOnClickListener { itemClickListener(this) }
            }
        }
    }
}