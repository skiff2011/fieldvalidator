package com.skiff2011.fieldvalidator

import com.skiff2011.fieldvalidator.bus.GreenRobotValidationBusFactory
import com.skiff2011.fieldvalidator.bus.ValidationBusFactory
import com.skiff2011.fieldvalidator.field.AutoValidateableField

class OptionalAutoValidator(validationBusFactory: ValidationBusFactory = GreenRobotValidationBusFactory()) :
  AutoValidator(validationBusFactory) {

  private val validateableFieldsIds = mutableSetOf<Int>()

  fun enableAll() {
    validateableFieldsIds.addAll(super.getValidateableFields().map { field -> field.viewId })
    getValidateableFields().forEach { field -> field.callback = this }
    invalidateValidatorState()
  }

  fun disableAll() {
    getValidateableFields().forEach { field -> field.callback = null }
    validateableFieldsIds.clear()
    errors.clear()
    invalidateValidatorState()
  }

  fun enable(vararg ids: Int) {
    validateableFieldsIds.addAll(ids.toList())
    getValidateableFields().filter { field -> ids.contains(field.viewId) }
      .forEach { field -> field.callback = this }
    invalidateValidatorState()
  }

  fun disable(vararg ids: Int) {
    getValidateableFields().forEach { field -> field.callback = this }
    validateableFieldsIds.removeAll(ids.toList())
    ids.forEach { id -> errors.remove(id) }
    invalidateValidatorState()

  }

  override fun getValidateableFields(): List<AutoValidateableField<*>> {
    return super.getValidateableFields()
      .filter { field -> validateableFieldsIds.contains(field.viewId) }
  }
}