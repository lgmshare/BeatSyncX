package com.changhong.beatsyncx.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.changhong.beatsyncx.R
import com.changhong.beatsyncx.models.Heartbeat
import com.changhong.beatsyncx.utils.Utils

class RecordAdapter : RecyclerView.Adapter<RecordAdapter.ItemViewHolder>() {

    var dataList: ArrayList<Heartbeat> = arrayListOf()

    inner class ItemViewHolder : RecyclerView.ViewHolder {

        val stage_level: ImageView
        val stage_title: TextView
        val tv_max: TextView
        val tv_min: TextView
        val tv_ave: TextView
        val tv_datetime: TextView
        val btn_edit: View

        constructor(item: View) : super(item) {
            stage_level = item.findViewById(R.id.iv_level)
            stage_title = item.findViewById(R.id.tv_title)
            tv_max = item.findViewById(R.id.tv_max)
            tv_min = item.findViewById(R.id.tv_min)
            tv_ave = item.findViewById(R.id.tv_ave)
            tv_datetime = item.findViewById(R.id.tv_datetime)
            btn_edit = item.findViewById(R.id.btn_edit)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataList[position]
        when (item.level()) {
            0 -> {
                holder.stage_level.setImageResource(R.mipmap.heartbeat_level_1)
                holder.stage_title.setText(R.string.heartbeat_level_slow)
            }

            1 -> {
                holder.stage_level.setImageResource(R.mipmap.heartbeat_level_2)
                holder.stage_title.setText(R.string.heartbeat_level_normal)
            }

            2 -> {
                holder.stage_level.setImageResource(R.mipmap.heartbeat_level_3)
                holder.stage_title.setText(R.string.heartbeat_level_fast)
            }
        }

        holder.tv_max.text = item.max.toString()
        holder.tv_min.text = item.min.toString()
        holder.tv_ave.text = item.ave.toString()
        holder.tv_datetime.text = Utils.formatDatetime(item.datetime)

        holder.btn_edit.setOnClickListener {
            itemClickCallback?.invoke(item)
        }
    }

    var itemClickCallback: ((Heartbeat) -> Unit)? = null

}