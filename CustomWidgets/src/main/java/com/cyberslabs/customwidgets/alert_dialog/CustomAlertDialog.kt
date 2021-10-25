@file:Suppress("unused")

package com.cyberslabs.customwidgets.alert_dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
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
import androidx.appcompat.content.res.AppCompatResources
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
import com.cyberslabs.customwidgets.databinding.DialogAlertCustomBinding
import com.cyberslabs.customwidgets.viewBinding
import java.lang.Exception

open class CustomAlertDialog : DialogFragment() {

    private val binding: DialogAlertCustomBinding by viewBinding()

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
    private var onDismissListener: (() -> Unit)? = null

    fun setTitle(title: String) {
        this.title = title
    }

    fun setOnDismissListener(onDismiss: () -> Unit) {
        onDismissListener = onDismiss
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

    fun setSpannable(textFromMsg: String, textFromMsgTwo: String, textFromMsgThree: String, spinnerCallback: OnThreeSpannerClickListener) {
        this.spannableText = textFromMsg
        this.spannableTextTwo = textFromMsgTwo
        this.spannableTextThree = textFromMsgThree
        this.spanThreeCallback = spinnerCallback
    }

    fun setMultiChoiceItems(itemList: ArrayList<String>, checkList: ArrayList<Boolean>, listener: OnMultiChoiceClickListener) {
        this.itemList = itemList
        this.checkList = checkList
        this.onMultiChoicelistener = listener
        fromMultiChoice = true
    }

    fun setMultiChoiceItems(itemList: ArrayList<String>, checkList: ArrayList<Boolean>, iconIdList: ArrayList<Int>, listener: OnMultiChoiceClickListener) {
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
            Log.e("CustomAlertDialog", "To setMaxCheckedForMultichoice you must initialize setMultiChoiceItems")
        }
    }

    fun setSingleChoiceItems(itemList: ArrayList<String>, checkedDefault: Int, listener: OnSingleChoiceClickListener) {
        this.itemList = itemList
        this.singleCheckedDefault = checkedDefault
        this.onSingleChoiceClickListener = listener
        fromSingleChoice = true
    }

    fun setSingleChoiceItems(itemList: ArrayList<String>, iconIdList: ArrayList<Int>, checkedDefault: Int, listener: OnSingleChoiceClickListener) {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // clear edit text in android 6.0.1 without click listener don't work
        binding.root.setOnClickListener {}
        return binding.root
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
                binding.ivTilIcon.visibility = View.VISIBLE
                try {
                    binding.ivTilIcon.setBackgroundResource(tilIcon!!)
                    binding.ivTilIcon.visibility = View.VISIBLE
                } catch (e: Exception) {
                    binding.ivTilIcon.visibility = View.GONE
                    Log.e("CustomAlertDialog", "Wrong format of picture (format: R.drawable.ic_.....")
                }
            }
            if (binding.scvList.visibility != View.VISIBLE) {

                binding.llTextInput.visibility = View.VISIBLE
                binding.tilInput.visibility = View.VISIBLE
                binding.tilInput.hint = hint
                onTextInputListener?.getTextInputLayout(binding.tilInput)
                binding.tilInput.editText?.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) hidekeyboard() }
            } else Log.e("CustomAlertDialog", "Can't use MultiChoice or SingleChoice with TextInput")
        }
    }

    private fun setChoiceLayout() {
        if (fromMultiChoice && !fromSingleChoice) setMultiChoiceLayout()
        if (!fromMultiChoice && fromSingleChoice) setSingleChoiceLayout()
        if (fromMultiChoice && fromSingleChoice) Log.e("CustomAlertDialog", "Can't use MultiChoice and SingleChoice at same time")
    }

    private fun setMultiChoiceLayout() {
        binding.scvList.visibility = View.VISIBLE
        var setMaxCheck = false

        if (maxChecked > 0 && defaultChecked < maxChecked) setMaxCheck = true else Log.e("CustomAlertDialog", "MaxChecked value must be the bigger than checked values")

        if (!itemList.isNullOrEmpty() && !checkList.isNullOrEmpty()) {
            val adapterMulti = MultiChoiceAdapter(itemList!!, checkList!!, onMultiChoicelistener!!)
            if (!iconList.isNullOrEmpty()) adapterMulti.setIconList(iconList!!)
            if (setMaxCheck) adapterMulti.setMaxCheck(maxChecked, defaultChecked, maxListener!!)
            binding.rvChoiceView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = adapterMulti
                setHasFixedSize(true)
            }
        } else {
            Log.e("CustomAlertDialog", "List is Empty")
        }
    }

    private fun setSingleChoiceLayout() {
        binding.scvList.visibility = View.VISIBLE
        if (!itemList.isNullOrEmpty()) {
            val adapterSingle = SingleChoiceAdapter(itemList!!, singleCheckedDefault, onSingleChoiceClickListener!!)
            if (!iconList.isNullOrEmpty()) adapterSingle.setIconList(iconList!!)
            binding.rvChoiceView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = adapterSingle
                setHasFixedSize(true)
            }
        } else {
            Log.e("CustomAlertDialog", "List is Empty")
        }
    }

    private fun setIconLayout() {
        if (icon != null) binding.ivIcon.setImageResource(icon!!) else binding.ivIcon.visibility = View.GONE
    }

    private fun setTitleLayout() {
        if (!title.isNullOrEmpty()) binding.tvTitle.text = title else binding.tvTitle.visibility = View.GONE
    }

    private fun setTitleColor() {
        if (coloredTitle) {
            binding.llTitleHolder.background =
                ResourcesCompat.getDrawable(resources, R.drawable.title_background, null)
            binding.tvTitle.apply {
                setTextColor(ContextCompat.getColor(requireContext(), titleColor!!))
                textSize = 24f
                setTypeface(null, Typeface.ITALIC)
            }
            binding.flTitleBottomMargine.visibility = View.VISIBLE
        }
    }

    private fun setMsgLayout() {
        if (msg != null) binding.tvMsg.text = msg else binding.tvMsg.visibility = View.GONE
        if (inCenter) binding.tvMsg.gravity = Gravity.CENTER
    }

    private fun setButtonLayout() {
        if (positiveBtnText == null && negativeBtnText == null) {
            binding.llButtonLayout.visibility = View.GONE
        } else {
            if (positiveBtnText != null && negativeBtnText == null) binding.btnNegative.visibility = View.INVISIBLE
            if (positiveBtnText == null && negativeBtnText != null) binding.btnPositive.visibility = View.INVISIBLE
            if (positiveBtnText != null) setupPositiveButton()
            if (negativeBtnText != null) setupNegativeButton()
        }
    }

    @SuppressLint("ResourceType")
    private fun setupPositiveButton() {
        if (!positiveBtnInFocus) {
            binding.btnPositive.setBackgroundResource(R.drawable.dialog_btn_out_focus_transparetn)
            binding.btnPositive.setTextColor(AppCompatResources.getColorStateList(requireContext(), R.drawable.btn_transparent_out_focus_color_selector))
        }
        binding.btnPositive.text = positiveBtnText
        binding.btnPositive.setOnClickListener {
            positiveBtnListener?.onClick()
        }
    }

    @SuppressLint("ResourceType")
    private fun setupNegativeButton() {
        if (!negativeBtnInFocus) {
            binding.btnNegative.setBackgroundResource(R.drawable.dialog_btn_out_focus_transparetn)
            binding.btnNegative.setTextColor(AppCompatResources.getColorStateList(requireContext(), R.drawable.btn_transparent_out_focus_color_selector))
        }
        binding.btnNegative.text = negativeBtnText
        binding.btnNegative.setOnClickListener {
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

    private fun setTwoSpan() {
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
            makeLinks(msg!!, arrayOf(spannableText!!, spannableTextTwo!!), arrayOf(clickableSpan, clickableSpan2))
        } else {
            Log.e("CustomAlertDialog", "No Message Set")
        }
    }

    private fun setThreeSpan() {
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
            binding.tvMsg.text = spannableString
        }
    }

    private fun setMsgClickable() {
        binding.tvMsg.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun hidekeyboard() {
        try {
            val windowToken = dialog?.window?.decorView?.rootView
            val inputMethodManager = dialog?.context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(windowToken?.windowToken, 0)
        } catch (e: Exception) {
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.invoke()
    }
}
