package com.skiff2011.fieldvalidator

import com.skiff2011.fieldvalidator.bus.GreenRobotValidationBusFactory
import com.skiff2011.fieldvalidator.bus.ValidationBusFactory
import com.skiff2011.fieldvalidator.condition.Error
import com.skiff2011.fieldvalidator.field.FieldFactory
import com.skiff2011.fieldvalidator.field.ValidateableField
import com.skiff2011.fieldvalidator.field.ValidateableFieldFactory

class FieldValidator(
  busFactory: ValidationBusFactory = GreenRobotValidationBusFactory(),
  fieldFactory: FieldFactory = ValidateableFieldFactory()
) : AbsFieldValidator(busFactory, fieldFactory) {

  fun validate(fieldIds: List<Int>? = null): Boolean =
    performValidation(if (fieldIds == null) {
      fields().values.toList()
    } else {
      fields().values.filter { field -> fieldIds.contains(field.viewId) }
    })

  private fun performValidation(fields: List<ValidateableField<*>>): Boolean =
    fields.map { field ->
      field.validate()
    }.find { result -> result is Error } == null
}