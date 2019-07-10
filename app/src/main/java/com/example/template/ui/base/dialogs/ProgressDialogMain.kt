package com.example.template.ui.base.dialogs

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.template.R
import kotlinx.android.synthetic.main.dialog_main_progress.*
import javax.inject.Inject


class ProgressDialogMain @Inject constructor() : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity)
        dialog.setContentView(R.layout.dialog_main_progress)


        return dialog
    }
}


