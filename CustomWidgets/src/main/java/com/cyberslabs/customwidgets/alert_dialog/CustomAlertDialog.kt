package com.cyberslabs.customwidgets.alert_dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyberslabs.customwidgets.R
import com.cyberslabs.customwidgets.alert_dialog.adapters.MultiChoiceAdapter
import com.cyberslabs.customwidgets.alert_dialog.adapters.SingleChoiceAdapter
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnButtonClickListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnMaxReached
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnMultiChoiceClickListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnSingleChoiceClickListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnSpannerClickListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnTextInputListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnThreeSpannerClickListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnTwoSpannerClickListener
import java.lang.Exception
import kotlinx.android.synthetic.main.dialog_alert_custom.*

open class CustomAlertDialog : DialogFragment() {

    private var title: String? = null
    private var msg: String? = null
    private var inCenter = false
    private var icon: Int? = null
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
    private var itemList: ArrayList<String>? = null
    private var checkList: ArrayList<Boolean>? = null
    private var iconList: ArrayList<Int>? = null
    private var fromMultiChoice = false
    private var fromSingleChoice = false
    private var onMultiChoicelistener: OnMultiChoiceClickListener? = null
    private var onSingleChoiceClickListener: OnSingleChoiceClickListener? = null
    private var onTextInputListener: OnTextInputListener? = null
    private var maxListener: OnMaxReached? = null
    private var coloredTitle = false
    private var titleColor: Int? = null
    private var singleCheckedDefault = -1
    private var maxChecked = 0
    private var defaultChecked = 0
    private var hint: String? = null
    private var tilIcon: Int? = null

    fun setTitle(title: String) {
        this.title = title
    }

    fun setTitle(title: String, coloredTitle: Boolean, titleColorId: Int) {
        this.title = title
        this.coloredTitle = coloredTitle
        this.titleColor = titleColorId
    }

    fun setMessage(msg: String) {
        this.msg = msg
    }

    fun setMessage(msg: String, inCenter: Boolean) {
        this.msg = msg
        this.inCenter = inCenter
    }

    fun setIcon(iconId: Int) {
        this.icon = iconId
    }

    fun setPositiveButton(btnText: String, onButtonClickListener: OnButtonClickListener) {
        positiveBtnText = btnText
        positiveBtnListener = onButtonClickListener
    }

    fun setNegativeButton(btnText: String, onButtonClickListener: OnButtonClickListener) {
        negativeBtnText = btnText
        negativeBtnListener = onButtonClickListener
    }

    fun setPositiveButton(
        btnText: String,
        inFocus: Boolean,
        onButtonClickListener: OnButtonClickListener
    ) {
        positiveBtnText = btnText
        positiveBtnInFocus = inFocus
        positiveBtnListener = onButtonClickListener
    }

    fun setNegativeButton(
        btnText: String,
        inFocus: Boolean,
        onButtonClickListener: OnButtonClickListener
    ) {
        negativeBtnText = btnText
        negativeBtnInFocus = inFocus
        negativeBtnListener = onButtonClickListener
    }

    fun setSpannable(textFromMsg: String, spinnerCallback: OnSpannerClickListener) {
        this.spannableText = textFromMsg
        this.spanCallback = spinnerCallback
    }

    fun setSpannable(
        textFromMsg: String,
        textFromMsgTwo: String,
        spinnerCallback: OnTwoSpannerClickListener
    ) {
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

    fun setMultiChoiceItems(
        itemList: ArrayList<String>,
        checkList: ArrayList<Boolean>,
        listener: OnMultiChoiceClickListener
    ) {
        this.itemList = itemList
        this.checkList = checkList
        this.onMultiChoicelistener = listener
        fromMultiChoice = true
    }

    fun setMultiChoiceItems(
        itemList: ArrayList<String>,
        checkList: ArrayList<Boolean>,
        iconIdList: ArrayList<Int>,
        listener: OnMultiChoiceClickListener
    ) {
        this.itemList = itemList
        this.checkList = checkList
        this.iconList = iconIdList
        this.onMultiChoicelistener = listener
        fromMultiChoice = true
    }

    fun setMaxCheckedForMultichoice(maxChecked: Int, maxListener: OnMaxReached) {
        this.maxChecked = maxChecked
        this.maxListener = maxListener
        calculateDefaultCheckSize()
    }

    private fun calculateDefaultCheckSize() {
        if (!checkList.isNullOrEmpty()) {
            checkList?.forEach { if (it) defaultChecked++ }
        } else {
            Log.e(
                "CustomAlertDialog",
                "To setMaxCheckedForMultichoice you must initialize setMultiChoiceItems"
            )
        }
    }

    fun setSingleChoiceItems(
        itemList: ArrayList<String>,
        checkedDefault: Int,
        listener: OnSingleChoiceClickListener
    ) {
        this.itemList = itemList
        this.singleCheckedDefault = checkedDefault
        this.onSingleChoiceClickListener = listener
        fromSingleChoice = true
    }

    fun setSingleChoiceItems(
        itemList: ArrayList<String>,
        iconIdList: ArrayList<Int>,
        checkedDefault: Int,
        listener: OnSingleChoiceClickListener
    ) {
        this.itemList = itemList
        this.iconList = iconIdList
        this.singleCheckedDefault = checkedDefault
        this.onSingleChoiceClickListener = listener
        fromSingleChoice = true
    }

    fun setTextInput(hint: String, listener: OnTextInputListener) {
        this.hint = hint
        this.onTextInputListener = listener
    }

    fun setTextInput(hint: String, iconId: Int, listener: OnTextInputListener) {
        this.hint = hint
        this.tilIcon = iconId
        this.onTextInputListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogCustomAlertStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = inflater.inflate(R.layout.dialog_alert_custom, container, false)
        // clear edit text in android 6.0.1 without click listener don't work
        view.setOnClickListener {}
        return view
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
        setChoiceLayout()
        setTitleColor()
        setTextInputLayout()
    }

    private fun setTextInputLayout() {
        if (hint != null) {
            if (tilIcon != null) {
                ivTilIcon.visibility = View.VISIBLE
                try {
                    ivTilIcon.setBackgroundResource(tilIcon!!)
                    ivTilIcon.visibility = View.VISIBLE
                } catch (e: Exception) {
                    ivTilIcon.visibility = View.GONE
                    Log.e(
                        "CustomAlertDialog",
                        "Wrong format of picture (format: R.drawable.ic_....."
                    )
                }
            }
            if (scvList.visibility != View.VISIBLE) {

                llTextInput.visibility = View.VISIBLE
                tilInput.visibility = View.VISIBLE
                tilInput.hint = hint
                onTextInputListener?.getTextInputLayout(tilInput)
                tilInput.editText?.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) hidekeyboard() }
            } else Log.e(
                "CustomAlertDialog",
                "Can't use MultiChoice or SingleChoice with TextInput"
            )
        }
    }

    private fun setChoiceLayout() {
        if (fromMultiChoice && !fromSingleChoice) setMultiChoiceLayout()
        if (!fromMultiChoice && fromSingleChoice) setSingleChoiceLayout()
        if (fromMultiChoice && fromSingleChoice) Log.e(
            "CustomAlertDialog",
            "Can't use MultiChoice and SingleChoice at same time"
        )
    }

    private fun setMultiChoiceLayout() {
        scvList.visibility = View.VISIBLE
        var setMaxCheck = false

        if (maxChecked > 0 && defaultChecked < maxChecked) setMaxCheck = true else Log.e(
            "CustomAlertDialog",
            "MaxChecked value must be the bigger than checked values"
        )

        if (!itemList.isNullOrEmpty() && !checkList.isNullOrEmpty()) {
            val adapterMulti = MultiChoiceAdapter(itemList!!, checkList!!, onMultiChoicelistener!!)
            if (!iconList.isNullOrEmpty()) adapterMulti.setIconList(iconList!!)
            if (setMaxCheck) adapterMulti.setMaxCheck(maxChecked, defaultChecked, maxListener!!)
            rvChoiceView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = adapterMulti
                setHasFixedSize(true)
            }
        } else {
            Log.e("CustomAlertDialog", "List is Empty")
        }
    }

    private fun setSingleChoiceLayout() {
        scvList.visibility = View.VISIBLE
        if (!itemList.isNullOrEmpty()) {
            val adapterSingle =
                SingleChoiceAdapter(itemList!!, singleCheckedDefault, onSingleChoiceClickListener!!)
            if (!iconList.isNullOrEmpty()) adapterSingle.setIconList(iconList!!)
            rvChoiceView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = adapterSingle
                setHasFixedSize(true)
            }
        } else {
            Log.e("CustomAlertDialog", "List is Empty")
        }
    }

    private fun setIconLayout() {
        if (icon != null) ivIcon.setImageResource(icon!!) else ivIcon.visibility = View.GONE
    }

    private fun setTitleLayout() {
        if (!title.isNullOrEmpty()) tvTitle.text = title else tvTitle.visibility = View.GONE
    }

    private fun setTitleColor() {
        if (coloredTitle) {
            llTitleHolder.background =
                ResourcesCompat.getDrawable(getResources(), R.drawable.title_background, null)
            tvTitle.apply {
                setTextColor(ContextCompat.getColor(context!!, titleColor!!))
                textSize = 24f
                setTypeface(null, Typeface.ITALIC)
            }
            flTitleBottomMargine.visibility = View.VISIBLE
        }
    }

    private fun setMsgLayout() {
        if (msg != null) tvMsg.text = msg else tvMsg.visibility = View.GONE
        if (inCenter) tvMsg.gravity = Gravity.CENTER
    }

    private fun setButtonLayout() {
        if (positiveBtnText == null && negativeBtnText == null) {
            llButtonLayout.visibility = View.GONE
        } else {
            if (positiveBtnText != null && negativeBtnText == null) btnNegative.visibility =
                View.INVISIBLE
            if (positiveBtnText == null && negativeBtnText != null) btnPositive.visibility =
                View.INVISIBLE
            if (positiveBtnText != null) setupPositiveButton()
            if (negativeBtnText != null) setupNegativeButton()
        }
    }

    @Suppress("DEPRECATION")
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

    @Suppress("DEPRECATION")
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
            makeLinks(
                msg!!,
                arrayOf(spannableText!!, spannableTextTwo!!),
                arrayOf(clickableSpan, clickableSpan2)
            )
        } else {
            Log.e("CustomAlertDialog", "No Message Set")
        }
    }

    fun setThreeSpan() {
        if (msg != null) {
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

    private fun hidekeyboard() {
        try {
            val windowToken = dialog?.window?.getDecorView()?.getRootView()
            val inputMethodManager =
                dialog?.context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(windowToken?.windowToken, 0)
        } catch (e: Exception) {
        }
    }
}
