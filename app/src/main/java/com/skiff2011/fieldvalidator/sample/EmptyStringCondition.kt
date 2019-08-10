package com.skiff2011.fieldvalidator.sample

import com.skiff2011.fieldvalidator.condition.Condition
import com.skiff2011.fieldvalidator.condition.Error
import com.skiff2011.fieldvalidator.condition.Valid
import com.skiff2011.fieldvalidator.condition.ValidationResult

class EmptyStringCondition : Condition<String> {
  override fun validate(value: String): ValidationResult {
    return if (value.isBlank()) {
      Error("Empty String")
    } else {
      Valid
    }
  }
}