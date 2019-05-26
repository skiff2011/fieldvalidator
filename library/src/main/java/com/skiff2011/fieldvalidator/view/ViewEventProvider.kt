package com.skiff2011.fieldvalidator.view

import android.view.View
import com.skiff2011.fieldvalidator.bus.ValidationEvent
import java.io.Serializable

class ViewEventProvider<V : View>(
  private val viewState: ValidationViewState<V>,
  private val viewClass: Class<V>
) :
  Serializable {

  fun generateViewStateEvent(error: String?): ValidationEvent = object : ValidationEvent {
    override fun performAction(view: View) {
      if (!viewClass.isInstance(view)) {
        throw TypeCastException("ValidationViewState type differs from the type of view")
      }
      val validateableView = viewClass.cast(view)!!
      if (error == null) {
        viewState.hideError(validateableView)
      } else {
        viewState.showError(error, validateableView)
      }
    }
  }
}