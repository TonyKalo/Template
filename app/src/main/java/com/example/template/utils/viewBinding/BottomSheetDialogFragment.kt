package com.example.template.utils.viewBinding

import android.view.LayoutInflater
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Yoga C. Pranata on 07/12/2020.
 * Android Engineer
 *
 * How to use:
 * - Call it inside your BottomsheetDialogFragment
 * private val binding: BottomsheetSampleBinding by viewBinding()
 * and access it normally like you use local variable.
 */
inline fun <reified T : ViewBinding> BottomSheetDialogFragment.viewBinding() = BottomSheetViewBindingDelegate(T::class.java, this)

class BottomSheetViewBindingDelegate<T : ViewBinding>(private val bindingClass: Class<T>, private val dialogFragment: BottomSheetDialogFragment) : ReadOnlyProperty<BottomSheetDialogFragment, T> {
    /**
     * initiate variable for binding view
     */
    private var binding: T? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: BottomSheetDialogFragment, property: KProperty<*>): T {
        binding?.let { return it }

        dialogFragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                dialogFragment.viewLifecycleOwnerLiveData.observe(dialogFragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            /**
                             * Clear the binding when Fragment lifecycle called the onDestroy
                             */
                            binding = null
                        }
                    })
                }
            }
        })

        /**
         * Checking the fragment lifecycle
         */
        val lifecycle = dialogFragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
        }

        /**
         * inflate View class
         */
        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)

        /**
         * Bind layout
         */
        val invokeLayout = inflateMethod.invoke(null, LayoutInflater.from(thisRef.context)) as T

        /**
         * Return binding layout
         */
        return invokeLayout.also { this.binding = it }
    }
}
