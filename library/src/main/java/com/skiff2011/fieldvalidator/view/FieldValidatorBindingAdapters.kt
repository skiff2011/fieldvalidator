package com.skiff2011.fieldvalidator.view

import android.databinding.BindingAdapter
import android.view.View
import android.view.View.OnAttachStateChangeListener
import com.skiff2011.fieldvalidator.AbstractValidator

class FieldValidatorBindingAdapters {
  companion object {
    @JvmStatic
    @BindingAdapter("validator")
    fun _bindValidator(view: View, validator: AbstractValidator<*>) {
      if (view.tag as? ValidationViewSubscriber != null) {
        return
      }
      val wrapper = validator.createSubscriber(view)
      view.tag = wrapper
      view.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
        override fun onViewDetachedFromWindow(v: View?) {
          validator.unsubscribe(wrapper)
        }

        override fun onViewAttachedToWindow(v: View?) {
          validator.subscribe(wrapper)
        }

      })
    }
  }
}