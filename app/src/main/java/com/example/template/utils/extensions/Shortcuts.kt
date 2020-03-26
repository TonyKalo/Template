package com.example.template.utils.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Any.log() = Log.e("TAG", toString())
fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
