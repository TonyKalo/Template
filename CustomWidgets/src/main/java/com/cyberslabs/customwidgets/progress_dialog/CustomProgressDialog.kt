package com.cyberslabs.customwidgets.progress_dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_alert_custom.*
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.util.Log
import com.cyberslabs.customwidgets.R
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnButtonClickListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnSpannerClickListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnThreeSpannerClickListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnTwoSpannerClickListener


class CustomProgressDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialog_progress_custom, container, false)
    }


}