package com.skiff2011.fieldvalidator.field

import com.skiff2011.fieldvalidator.condition.ValidationResult

interface FieldValidationCallback {
  fun onValidationResultChanged(viewId: Int, result: ValidationResult)
}
