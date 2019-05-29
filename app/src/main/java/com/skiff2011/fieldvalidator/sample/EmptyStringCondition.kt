package com.skiff2011.fieldvalidator.sample

import com.skiff2011.fieldvalidator.condition.Condition

class EmptyStringCondition : Condition<String> {
  override fun validate(value: String): String? {
    return if (value.isBlank()) {
      "Empty String"
    } else {
      null
    }
  }
}