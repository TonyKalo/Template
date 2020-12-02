@file:Suppress("MemberVisibilityCanBePrivate")

package com.cyberslabs.customwidgets.alert_dialog.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnSingleChoiceClickListener
import com.cyberslabs.customwidgets.databinding.SinglechoiceRowBinding
import java.lang.Exception

class SingleChoiceAdapter(val itemList: ArrayList<String>, var defaultCheck: Int, val listener: OnSingleChoiceClickListener) : RecyclerView.Adapter<SingleChoiceAdapter.ViewHolder>() {

    private var iconList = ArrayList<Int>()

    fun setIconList(iconList: ArrayList<Int>) { this.iconList = iconList }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = SinglechoiceRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            name.text = itemList[position]
            checkRadio.isChecked = defaultCheck == position

            holder.llRow.setOnClickListener {
                defaultCheck = position
                if (!checkRadio.isChecked) checkRadio.isChecked = true
                notifyDataSetChanged()
                listener.onClick(position)
            }
        }

        if (!iconList.isNullOrEmpty()) {
            if (iconList.size == itemList.size) {
                try {
                    holder.icon.setImageResource(iconList[position])
                    holder.icon.visibility = View.VISIBLE
                } catch (e: Exception) {
                    Log.e("CustomAlertDialog", "iconList value wrong")
                }
            } else {
                Log.e("CustomAlertDialog", "Item size and iconList size must be the same")
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(cadViewBinding: SinglechoiceRowBinding) : RecyclerView.ViewHolder(cadViewBinding.root) {
        val name: TextView = cadViewBinding.tvItem
        val icon: ImageView = cadViewBinding.ivIcon
        val checkRadio: RadioButton = cadViewBinding.rbItem
        val llRow: LinearLayout = cadViewBinding.llSinglechoiceRow
    }
}
