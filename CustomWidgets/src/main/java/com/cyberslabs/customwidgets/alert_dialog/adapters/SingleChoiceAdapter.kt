package com.cyberslabs.customwidgets.alert_dialog.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyberslabs.customwidgets.R
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnSingleChoiceClickListener
import kotlinx.android.synthetic.main.multichoice_row.view.*
import kotlinx.android.synthetic.main.multichoice_row.view.ivIcon
import kotlinx.android.synthetic.main.multichoice_row.view.tvItem
import kotlinx.android.synthetic.main.singlechoice_row.view.*


class SingleChoiceAdapter(val itemList:ArrayList<String>, val defaultCheck:Int, val listener: OnSingleChoiceClickListener) : RecyclerView.Adapter<SingleChoiceAdapter.ViewHolder>() {

    private var iconList=ArrayList<Int>()
    private var checkList=ArrayList<Boolean>()

    init {
        createCheckList()
    }



    fun setIconList(iconList:ArrayList<Int>){this.iconList=iconList}


    fun createCheckList(){
        itemList.forEachIndexed { index, i ->
            Log.e("TAG",defaultCheck.toString()+" "+checkList+" ")
            if (defaultCheck>=0&&index==defaultCheck)checkList.add(true)else checkList.add(false)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.singlechoice_row,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(itemList[position])
        holder.checkRadio.isChecked=checkList[position]

        holder.llRow.setOnClickListener{
            itemList.forEachIndexed { index, s ->
                if(position==index)checkList.add(true)else checkList.add(false)
            }
            notifyDataSetChanged()
        }
//
//        if(!iconList.isNullOrEmpty()) {
//            if (iconList.size == itemList.size) {
//                holder.icon.setImageResource(iconList[position])
//                holder.icon.visibility = View.VISIBLE
//            }else{
//                Log.e("CustomAlertDialog", "Item size and iconList size must be the same")
//            }
//        }
//
//        if(!checkList.isNullOrEmpty()){
//            if(checkList.size==itemList.size) {
//                if (checkList[position] == true) holder.checkBox.isChecked = true
//            }else{
//                Log.e("CustomAlertDialog", "Item size and checkList size must be the same")
//            }
//        }

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
        val checkRadio = itemView.rbItem
        val llRow = itemView.llSinglechoiceRow
    }



}