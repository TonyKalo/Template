package com.cyberslabs.customwidgets.alert_dialog.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyberslabs.customwidgets.R
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnMaxReached
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnMultiChoiceClickListener
import com.cyberslabs.customwidgets.databinding.MultichoiceRowBinding
import java.lang.Exception

class MultiChoiceAdapter(private val itemList: ArrayList<String>, private val checkList: ArrayList<Boolean>, private val listener: OnMultiChoiceClickListener) : RecyclerView.Adapter<MultiChoiceAdapter.ViewHolder>() {

    private var iconList = ArrayList<Int>()
    private var maxCheck = 0
    private var defaultCheck = 0
    private var onMaxReached: OnMaxReached? = null

    fun setIconList(iconList: ArrayList<Int>) { this.iconList = iconList }
    fun setMaxCheck(maxCheck: Int, defaultCheck: Int, maxListener: OnMaxReached) {
        this.maxCheck = maxCheck
        this.defaultCheck = defaultCheck
        this.onMaxReached = maxListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MultichoiceRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = itemList[position]

        if (maxCheck> 0) {
            initIfMaxCheckedIsInitialized(holder, position)
        } else {

            holder.llRow.setOnClickListener {
                holder.checkBox.performClick()
                listener.onClick(position, holder.checkBox.isChecked)
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

        if (!checkList.isNullOrEmpty()) {
            if (checkList.size == itemList.size) {
                if (checkList[position]) holder.checkBox.isChecked = true
            } else {
                Log.e("CustomAlertDialog", "Item size and checkList size must be the same")
            }
        }
    }

    private fun initIfMaxCheckedIsInitialized(holder: ViewHolder, position: Int) {
        holder.checkBox.isEnabled = true
        holder.llRow.setBackgroundResource(R.color.backgroundMainLight)
        if (maxCheck> defaultCheck) {
            holder.llRow.setOnClickListener {
                holder.checkBox.performClick()
                if (holder.checkBox.isChecked) {
                    defaultCheck++
                    checkList[position] = true
                } else {
                    checkList[position] = false
                    defaultCheck--
                }
                listener.onClick(position, holder.checkBox.isChecked)
                if (maxCheck == defaultCheck)onMaxReached?.onMax()
                notifyDataSetChanged()
            }
        } else {
            holder.checkBox.isEnabled = holder.checkBox.isChecked
            holder.llRow.setOnClickListener {
                if (holder.checkBox.isChecked) {

                    defaultCheck--
                    holder.checkBox.performClick()
                    checkList[position] = holder.checkBox.isChecked
                    listener.onClick(position, holder.checkBox.isChecked)
                } else {
                    onMaxReached?.onMax()
                }
                notifyDataSetChanged()
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

    class ViewHolder(binding: MultichoiceRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.tvItem
        val icon: ImageView = binding.ivIcon
        val checkBox: CheckBox = binding.cbItem
        val llRow: LinearLayout = binding.llMultichoiceRow
    }
}
