package com.cyberslabs.customalertdialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class CustomAlertDialog : DialogFragment() {




        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setStyle(STYLE_NORMAL, R.style.DialogCustomAlertStyle)

        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            getDialog().window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            // Do all the stuff to initialize your custom view

            return inflater.inflate(R.layout.dialog_alert_custom, container, false)
        }


}