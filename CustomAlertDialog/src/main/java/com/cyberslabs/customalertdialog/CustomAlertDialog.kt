package com.cyberslabs.customalertdialog

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


class CustomAlertDialog : DialogFragment() {


    private var title: String? = null
    private var msg: String? = null
    private var inCenter = false
    private var icon: Drawable? = null
    private var positiveBtnText: String? = null
    private var positiveBtnInFocus = true
    private var positiveBtnListener: OnButtonClickListener? = null
    private var negativeBtnText: String? = null
    private var negativeBtnInFocus = true
    private var negativeBtnListener: OnButtonClickListener? = null
    private var spannableText: String? = null
    private var spanCallback: OnSpannerClickListener? = null
    private var spannableTextTwo: String? = null
    private var spanTwoCallback: OnTwoSpannerClickListener? = null
    private var spannableTextThree: String? = null
    private var spanThreeCallback: OnThreeSpannerClickListener? = null

    fun setTitle(title: String) {
        this.title = title
    }

    fun setMessage(msg: String) {
        this.msg = msg
    }

    fun setMessage(msg: String, inCenter: Boolean) {
        this.msg = msg
        this.inCenter = inCenter
    }

    fun setIcon(icon: Drawable) {
        this.icon = icon
    }

    fun setPositiveButton(btnText: String, onButtonClickListener: OnButtonClickListener) {
        positiveBtnText = btnText
        positiveBtnListener = onButtonClickListener
    }

    fun setNegativeButton(btnText: String, onButtonClickListener: OnButtonClickListener) {
        negativeBtnText = btnText
        negativeBtnListener = onButtonClickListener
    }

    fun setPositiveButton(btnText: String, inFocus: Boolean, onButtonClickListener: OnButtonClickListener) {
        positiveBtnText = btnText
        positiveBtnInFocus = inFocus
        positiveBtnListener = onButtonClickListener
    }

    fun setNegativeButton(btnText: String, inFocus: Boolean, onButtonClickListener: OnButtonClickListener) {
        negativeBtnText = btnText
        negativeBtnInFocus = inFocus
        negativeBtnListener = onButtonClickListener
    }

    fun setSpannable(textFromMsg: String, spinnerCallback: OnSpannerClickListener) {
        this.spannableText = textFromMsg
        this.spanCallback = spinnerCallback
    }

    fun setSpannable(textFromMsg: String, textFromMsgTwo: String, spinnerCallback: OnTwoSpannerClickListener) {
        this.spannableText = textFromMsg
        this.spannableTextTwo = textFromMsgTwo
        this.spanTwoCallback = spinnerCallback
    }

    fun setSpannable(
        textFromMsg: String,
        textFromMsgTwo: String,
        textFromMsgThree: String,
        spinnerCallback: OnThreeSpannerClickListener
    ) {
        this.spannableText = textFromMsg
        this.spannableTextTwo = textFromMsgTwo
        this.spannableTextThree = textFromMsgThree
        this.spanThreeCallback = spinnerCallback
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

    private fun setViews() {
        setIconLayout()
        setTitleLayout()
        setMsgLayout()
        setButtonLayout()
        setSpannables()
    }

    private fun setIconLayout() {
        if (icon != null) ivIcon.setImageDrawable(icon) else ivIcon.visibility = View.GONE
    }

    private fun setTitleLayout() {
        if (title != null) tvTitle.text = title else tvTitle.visibility = View.GONE
    }

    private fun setMsgLayout() {
        if (msg != null) tvMsg.text = msg else tvMsg.visibility = View.GONE
        if (inCenter) tvMsg.gravity = Gravity.CENTER
    }

    private fun setButtonLayout() {
        if (positiveBtnText == null && negativeBtnText == null) {
            llButtonLayout.visibility = View.GONE
        } else {
            if (positiveBtnText != null && negativeBtnText == null) btnNegative.visibility = View.INVISIBLE
            if (positiveBtnText == null && negativeBtnText != null) btnPositive.visibility = View.INVISIBLE
            if (positiveBtnText != null) setupPositiveButton()
            if (negativeBtnText != null) setupNegativeButton()
        }


    }

    @SuppressLint("ResourceType")
    private fun setupPositiveButton() {
        if (!positiveBtnInFocus) {
            btnPositive.setBackgroundResource(R.drawable.dialog_btn_out_focus_transparetn)
            btnPositive.setTextColor(resources.getColorStateList(R.drawable.btn_transparent_out_focus_color_selector))
        }
        btnPositive.text = positiveBtnText
        btnPositive.setOnClickListener {
            positiveBtnListener?.onClick()
        }
    }

    @SuppressLint("ResourceType")
    private fun setupNegativeButton() {
        if (!negativeBtnInFocus) {
            btnNegative.setBackgroundResource(R.drawable.dialog_btn_out_focus_transparetn)
            btnNegative.setTextColor(resources.getColorStateList(R.drawable.btn_transparent_out_focus_color_selector))
        }
        btnNegative.text = negativeBtnText
        btnNegative.setOnClickListener {
            negativeBtnListener?.onClick()
        }
    }

    private fun setSpannables() {
        var spannableNum = 0
        if (spannableText != null) spannableNum++
        if (spannableTextTwo != null) spannableNum++
        if (spannableTextThree != null) spannableNum++
        if (spannableNum != 0) setMsgClickable()

        when (spannableNum) {
            1 -> setOneSpan()
            2 -> setTwoSpan()
            3 -> setThreeSpan()
        }
    }

    private fun setOneSpan() {
        if (msg != null) {
            val ss = SpannableString(msg)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    spanCallback?.onClick()

                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true

                }
            }
            makeLinks(msg!!, arrayOf(spannableText!!), arrayOf(clickableSpan))
        } else {
            Log.e("CustomAlertDialog", "No Message Set")
        }
    }

    fun setTwoSpan() {
        if (msg != null) {
            val ss = SpannableString(msg)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    spanTwoCallback?.onClickFirst()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true
                }
            }
            val clickableSpan2 = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    spanTwoCallback?.onClickSecond()

                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true

                }
            }
            makeLinks(msg!!, arrayOf(spannableText!!, spannableTextTwo!!), arrayOf(clickableSpan, clickableSpan2))
        } else {
            Log.e("CustomAlertDialog", "No Message Set")
        }
    }

    fun setThreeSpan() {
        if (msg != null) {
            val ss = SpannableString(msg)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    spanThreeCallback?.onClickFirst()

                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true

                }
            }
            val clickableSpan2 = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    spanThreeCallback?.onClickSecond()

                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true

                }
            }

            val clickableSpan3 = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    spanThreeCallback?.onClickThird()

                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true

                }
            }
            makeLinks(
                msg!!,
                arrayOf(spannableText!!, spannableTextTwo!!, spannableTextThree!!),
                arrayOf(clickableSpan, clickableSpan2, clickableSpan3)
            )
        } else {
            Log.e("CustomAlertDialog", "No Message Set")
        }
    }


    private fun makeLinks(msg: String, links: Array<String>, clickableSpans: Array<ClickableSpan>) {
        val spannableString = SpannableString(msg)
        for (i in links.indices) {
            val clickableSpan = clickableSpans[i]
            val link = links[i]

            val startIndexOfLink = msg.indexOf(link)
            if (startIndexOfLink < 0) {
                Log.e("CustomAlertDialog", "No SpanText ${i + 1} in Message")
            } else {
                spannableString.setSpan(
                    clickableSpan, startIndexOfLink, startIndexOfLink + link.length,
                    Spanned.SPAN_INCLUSIVE_INCLUSIVE
                )
            }
            tvMsg.text = spannableString
        }
    }

    private fun setMsgClickable() {
        tvMsg.movementMethod = LinkMovementMethod.getInstance()
    }
}