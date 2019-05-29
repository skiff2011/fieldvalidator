package com.skiff2011.fieldvalidator

import com.skiff2011.fieldvalidator.bus.GreenRobotValidationBusFactory
import com.skiff2011.fieldvalidator.bus.ValidationBusFactory
import com.skiff2011.fieldvalidator.field.SimpleValidateableFieldFactory
import com.skiff2011.fieldvalidator.field.ValidateableField

class SimpleValidator(
  validationBusFactory: ValidationBusFactory = GreenRobotValidationBusFactory()
) :
  AbstractValidator<ValidateableField<*>>(validationBusFactory, SimpleValidateableFieldFactory()) {
}