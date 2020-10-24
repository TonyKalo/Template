package com.example.template.utils.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


fun Fragment.getNavigationResult(key: String = "result"):Any? =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Any>(key)?.value

fun Fragment.setNavigationResult(key: String = "result",result: Any) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}