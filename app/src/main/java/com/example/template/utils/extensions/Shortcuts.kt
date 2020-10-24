@file:Suppress("unused")
package com.example.template.utils.extensions

import android.util.Log

fun Any.log() = Log.e("TAG", toString())
fun Any.arcGisLog() = Log.e("ARCGIS", toString())
