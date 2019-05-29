package com.skiff2011.fieldvalidator

import com.skiff2011.fieldvalidator.bus.GreenRobotValidationBusFactory
import com.skiff2011.fieldvalidator.bus.ValidationBusFactory
import com.skiff2011.fieldvalidator.field.AutoValidateableField
import com.skiff2011.fieldvalidator.field.AutoValidateableFieldFactory

open class AutoValidator(
  validationBusFactory: ValidationBusFactory = GreenRobotValidationBusFactory()
) :
  AbstractValidator<AutoValidateableField<*>>(validationBusFactory, AutoValidateableFieldFactory()),
  FieldValidationCallback {

  private var callback: ValidationCallback? = null
    set(value) {
      if (field != value) {
        field = value
        val t = if (value == null) null else this
        getValidateableFields().forEach { f ->
          f.callback = t
        }
        if (t != null) {
          invalidateValidatorState()
        }
      }
    }

  private var valid: Boolean = true
    set(value) {
      if (field != value) {
        field = value
        callback?.onValidStatusChanged(value)
      }
    }

  protected val errors: HashMap<Int, Boolean> = HashMap()

  fun setValidationCallback(callback: ValidationCallback) {
    this.callback = callback
  }

  fun removeValidationCallback() {
    this.callback = null
  }

  override fun validate(): Boolean = if (callback == null) {
    super.validate()
  } else {
    valid
  }

  override fun onErrorChanged(viewId: Int, error: String?) {
    errors[viewId] = error != null
    invalidateValidatorState()
    applyViewState(viewId, error)
  }

  protected open fun getValidateableFields(): List<AutoValidateableField<*>> =
    fields().values.toList()

  protected fun invalidateValidatorState() {
    valid = errors.values.firstOrNull { error ->
      error
    } == null
  }

}