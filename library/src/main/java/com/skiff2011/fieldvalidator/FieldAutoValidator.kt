package com.skiff2011.fieldvalidator

import com.skiff2011.fieldvalidator.bus.GreenRobotValidationBusFactory
import com.skiff2011.fieldvalidator.bus.ValidationBusFactory
import com.skiff2011.fieldvalidator.condition.Valid
import com.skiff2011.fieldvalidator.condition.ValidationResult
import com.skiff2011.fieldvalidator.field.FieldFactory
import com.skiff2011.fieldvalidator.field.FieldValidationCallback
import com.skiff2011.fieldvalidator.field.ValidateableFieldFactory

class FieldAutoValidator(
  busFactory: ValidationBusFactory = GreenRobotValidationBusFactory(),
  fieldFactory: FieldFactory = ValidateableFieldFactory(),
  private val validationCallback: ValidationCallback
) : AbsFieldValidator(busFactory, fieldFactory), FieldValidationCallback {

  private var statuses: MutableMap<Int, Boolean> = mutableMapOf()

  val isValid: Boolean
    get() {
      return _isValid
    }

  private var _isValid: Boolean = true
    set(value) {
      field = value
      validationCallback.onValidStatusChanged(value)
    }

  fun enableAutoValidation(fieldIds: List<Int>? = null) {
    val fields = if (fieldIds == null) {
      fields().values.toList()
    } else {
      fields().values.filter { field -> fieldIds.contains(field.viewId) }
    }
    fields.forEach { field ->
      field.autoValidateable = true
    }
    statuses.putAll(fields.associate { field ->
      field.viewId to (field.validationResult == Valid)
    })
    invalidateStatus()
  }

  fun disableAutoValidation(fieldIds: List<Int>? = null) {
    val fields = if (fieldIds == null) {
      fields().values.toList()
    } else {
      fields().values.filter { field -> fieldIds.contains(field.viewId) }
    }
    fields.forEach { field ->
      field.autoValidateable = false
      statuses.remove(field.viewId)
    }
    invalidateStatus()
  }

  private fun invalidateStatus() {
    _isValid = statuses.values.firstOrNull { status -> !status } == null
  }

  override fun onValidationResultChanged(viewId: Int, result: ValidationResult) {
    statuses[viewId] = result == Valid
    invalidateStatus()
  }

}