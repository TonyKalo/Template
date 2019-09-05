package com.cyberslabs.customwidgets.alert_dialog.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyberslabs.customwidgets.R
import kotlinx.android.synthetic.main.multichoice_row.view.*
import android.content.Context.LAYOUT_INFLATER_SERVICE
import androidx.core.content.ContextCompat.getSystemService

import android.content.Context





class MultichoiceAdapter(val itemList:ArrayList<String>) : RecyclerView.Adapter<MultichoiceAdapter.ViewHolder>() {


    private var checkList=ArrayList<Boolean>()
    private var iconList=ArrayList<Int>()



    constructor( itemList:ArrayList<String>,iconList:ArrayList<Int>) : this(itemList){
        this.iconList=iconList
    }
    constructor( itemList:ArrayList<String>,iconList:ArrayList<Int>,checkList:ArrayList<Boolean>) : this(itemList){
        this.iconList=iconList
        this.checkList=checkList

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.multichoice_row,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(itemList[position])

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
    }



}