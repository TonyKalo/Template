package com.cyberslabs.customwidgets.alert_dialog.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyberslabs.customwidgets.R
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnMultiChoiceClickListener
import kotlinx.android.synthetic.main.multichoice_row.view.*


class MultiChoiceAdapter(val itemList:ArrayList<String>, val checkList:ArrayList<Boolean>, val listener: OnMultiChoiceClickListener) : RecyclerView.Adapter<MultiChoiceAdapter.ViewHolder>() {



    private var iconList=ArrayList<Int>()

    fun setIconList(iconList:ArrayList<Int>){this.iconList=iconList}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.multichoice_row,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(itemList[position])

        holder.llRow.setOnClickListener{
            holder.checkBox.performClick()
            listener.onClick(position,holder.checkBox.isChecked)
        }

        if(!iconList.isNullOrEmpty()) {
            if (iconList.size == itemList.size) {
                holder.icon.setImageResource(iconList[position])
                holder.icon.visibility = View.VISIBLE
            }else{
                Log.e("CustomAlertDialog", "Item size and iconList size must be the same")
            }
        }

        if(!checkList.isNullOrEmpty()){
            if(checkList.size==itemList.size) {
                if (checkList[position] == true) holder.checkBox.isChecked = true
            }else{
                Log.e("CustomAlertDialog", "Item size and checkList size must be the same")
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


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.tvItem
        val icon = itemView.ivIcon
        val checkBox = itemView.cbItem
        val llRow = itemView.llMultichoiceRow
    }



}