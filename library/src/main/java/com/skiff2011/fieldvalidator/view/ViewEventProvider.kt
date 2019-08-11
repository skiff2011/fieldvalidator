package com.skiff2011.fieldvalidator.view

import android.view.View
import com.skiff2011.fieldvalidator.bus.ValidationEvent
import com.skiff2011.fieldvalidator.condition.Error
import com.skiff2011.fieldvalidator.condition.ValidationResult

class ViewEventProvider<V : View>(
  private val viewState: ValidationViewState<V>,
  private val viewClass: Class<V>
) {

  fun generateViewStateEvent(validateResult: ValidationResult): ValidationEvent =
    ValidationEvent { view ->
      if (!viewClass.isInstance(view)) {
        throw TypeCastException("ValidationViewState type differs from the type of view")
      }
      val validateableView = viewClass.cast(view)!!
      if (validateResult is Error) {
        viewState.showError(validateResult.error, validateableView)
      } else {
        viewState.hideError(validateableView)
      }
    }
}