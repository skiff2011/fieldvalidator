package com.skiff2011.fieldvalidator.field

import com.skiff2011.fieldvalidator.FieldValidationCallback
import com.skiff2011.fieldvalidator.condition.Condition
import java.io.Serializable
import kotlin.reflect.KProperty

class AutoValidateableField<T : Serializable?>(
  initialValue: T,
  viewId: Int,
  condition: Condition<T>,
  onValueChanged: ((T) -> Unit)? = null
) : ValidateableField<T>(initialValue, viewId, condition, onValueChanged) {

  override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    super.setValue(thisRef, property, value)
    validate()
  }

  var error: String? = null
    private set(value) {
      if (field != value) {
        field = value
        callback?.onErrorChanged(viewId, error)
      }
    }

  var callback: FieldValidationCallback? = null
    set(value) {
      if (field != value) {
        field = value
        validate()
      }
    }

  override fun validate(): String? = super.validate().also { validationResult ->
    error = validationResult
  }
}