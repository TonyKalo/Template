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

class SingleChoiceAdapter(private val itemList: ArrayList<String>, private var defaultCheck: Int, private val listener: OnSingleChoiceClickListener) : RecyclerView.Adapter<SingleChoiceAdapter.ViewHolder>() {

    private var iconList = ArrayList<Int>()

    fun setIconList(iconList: ArrayList<Int>) { this.iconList = iconList }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SinglechoiceRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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

    class ViewHolder(binding: SinglechoiceRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.tvItem
        val icon: ImageView = binding.ivIcon
        val checkRadio: RadioButton = binding.rbItem
        val llRow: LinearLayout = binding.llSinglechoiceRow
    }
}
