package com.cyberslabs.customalertdialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_alert_custom.*

class CustomAlertDialog : DialogFragment() {

    private var title:String? = null
    private var msg:String? = null
    private var icon:Drawable? = null
    private var positiveBtnText: String?=null
    private var positiveBtnListener: OnButtonClickListener?=null
    private var negativeBtnText: String?=null
    private var negativeBtnListener: OnButtonClickListener?=null

    fun setTitle(title:String){
        this.title=title
    }

    fun setMessage(msg:String){
        this.msg=msg
    }

    fun setIcon(icon:Drawable){
        this.icon=icon
    }

    fun setPositiveButton(btnText:String,onButtonClickListener: OnButtonClickListener){
        positiveBtnText=btnText
        positiveBtnListener=onButtonClickListener
    }

    fun setNegativeButton(btnText:String,onButtonClickListener: OnButtonClickListener){
        negativeBtnText=btnText
        negativeBtnListener=onButtonClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogCustomAlertStyle)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog().window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.dialog_alert_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
    }

    private fun setViews(){
        setIconLayout()
        setTitleLayout()
        setMsgLayout()
        setButtonLayout()
    }

    private fun setIconLayout(){
        if(icon!=null) ivIcon.setImageDrawable(icon) else ivIcon.visibility=View.GONE
    }

    private fun setTitleLayout(){
        if(title!=null) tvTitle.text = title else tvTitle.visibility=View.GONE
    }

    private fun setMsgLayout(){
        if(msg!=null) tvMsg.text = msg else tvMsg.visibility=View.GONE
    }

    private fun setButtonLayout(){
        if (positiveBtnText==null&&negativeBtnText==null){
            llButtonLayout.visibility=View.GONE
        }else{
            if(positiveBtnText != null && negativeBtnText==null) btnNegative.visibility=View.INVISIBLE
            if(positiveBtnText == null && negativeBtnText!=null) btnPositive.visibility=View.INVISIBLE
            if(positiveBtnText!=null) setupPositiveButton()
            if(negativeBtnText!=null) setupNegativeButton()
        }


    }

    private fun setupPositiveButton(){
        btnPositive.text=positiveBtnText
        btnPositive.setOnClickListener{
            positiveBtnListener?.onClick()
        }
    }

    private fun setupNegativeButton(){
        btnNegative.text=negativeBtnText
        btnNegative.setOnClickListener{
            negativeBtnListener?.onClick()
        }
    }
}