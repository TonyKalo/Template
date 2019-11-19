package com.example.template.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun String.log() = Log.e("TAG",toString())
fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()