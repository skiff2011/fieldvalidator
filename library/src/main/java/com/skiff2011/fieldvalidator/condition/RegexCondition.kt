package com.skiff2011.fieldvalidator.condition

open class RegexCondition(
  private val error: String,
  private val regex: Regex
) : Condition<String> {
  override fun validate(value: String): ValidationResult =
    if (value.matches(regex)) Valid else Error(error)
}