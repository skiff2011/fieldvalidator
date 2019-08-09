package com.skiff2011.fieldvalidator.view

import android.view.View
import androidx.annotation.CallSuper
import com.skiff2011.fieldvalidator.bus.ValidationEvent

abstract class ValidationViewSubscriber(private val view: View) {

  fun getId(): Int = view.id

  @CallSuper
  open fun onValidationEventReceived(validationEvent: ValidationEvent) {
    validationEvent.performAction(view)
  }
}