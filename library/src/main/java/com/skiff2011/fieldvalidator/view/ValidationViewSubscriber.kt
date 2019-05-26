package com.skiff2011.fieldvalidator.view

import android.support.annotation.CallSuper
import android.view.View
import com.skiff2011.fieldvalidator.bus.ValidationEvent

abstract class ValidationViewSubscriber(private val view: View) {

  fun getId(): Int = view.id

  @CallSuper
  open fun onValidationEventReceived(validationEvent: ValidationEvent) {
    validationEvent.performAction(view)
  }
}