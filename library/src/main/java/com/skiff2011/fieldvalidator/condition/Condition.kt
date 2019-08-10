package com.skiff2011.fieldvalidator.condition

interface Condition<T : Any?> {

  fun validate(value: T): ValidationResult

  fun thenValidate(nextCondition: Condition<T>): Condition<T> = object :
    Condition<T> {
    override fun validate(value: T): ValidationResult {
      return this@Condition.validate(value).let { result ->
        if (result is Valid) {
          nextCondition.validate(value)
        } else {
          result
        }
      }
    }
  }
}

sealed class ValidationResult
object Valid : ValidationResult()
data class Error(val error: String) : ValidationResult()