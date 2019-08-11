package com.skiff2011.fieldvalidator.condition

class NotNullAnyCondition<T : Any?>(private val error: String) : Condition<T> {
  override fun validate(value: T): ValidationResult = if (value != null) Valid else Error(error)
}