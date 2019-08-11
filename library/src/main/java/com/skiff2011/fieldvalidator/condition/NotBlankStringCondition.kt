package com.skiff2011.fieldvalidator.condition

class NotBlankStringCondition(private val error: String) : Condition<String> {
  override fun validate(value: String): ValidationResult =
    if (value.isNotBlank()) Valid else Error(error)
}