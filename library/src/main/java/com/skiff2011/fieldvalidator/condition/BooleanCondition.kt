package com.skiff2011.fieldvalidator.condition

class BooleanCondition(
  private val validBool: Boolean,
  private val error: String
) : Condition<Boolean> {
  override fun validate(value: Boolean): ValidationResult =
    if (value == validBool) Valid else Error(error)
}