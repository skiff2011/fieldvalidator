package com.skiff2011.fieldvalidator.view

import android.view.View
import android.view.View.OnAttachStateChangeListener
import androidx.databinding.BindingAdapter
import com.skiff2011.fieldvalidator.AbsFieldValidator

@BindingAdapter("validator")
fun bindValidator(view: View, validator: AbsFieldValidator) {
  if (view.tag as? ValidationViewSubscriber != null) {
    return
  }
  val wrapper = validator.createSubscriber(view)
  (view.tag as? ValidationViewSubscriber)?.let {
    validator.unsubscribe(it)
  }
  view.tag = wrapper
  if (view.windowToken != null) {
    validator.subscribe(wrapper)
  }
  view.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
    override fun onViewDetachedFromWindow(v: View?) {
      validator.unsubscribe(wrapper)
    }

    override fun onViewAttachedToWindow(v: View?) {
      validator.subscribe(wrapper)
    }

  })

}