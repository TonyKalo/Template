package com.example.template.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.view.MotionEvent
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


abstract class BaseActivity : AppCompatActivity() {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        @Suppress("IMPLICIT_BOXING_IN_IDENTITY_EQUALS")
        if (ev?.getAction() === MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.getRawX().toInt(), ev.getRawY().toInt())) {
                    v.isFocusableInTouchMode = false
                    v.clearFocus()
                    v.isFocusableInTouchMode = true
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}