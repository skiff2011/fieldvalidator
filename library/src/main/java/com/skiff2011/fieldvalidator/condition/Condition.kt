package com.skiff2011.fieldvalidator.condition

interface KCondition<T : Any?> {

  fun validate(value: T): ValidationResult

  fun thenValidate(nextCondition: KCondition<T>): KCondition<T> = object :
    KCondition<T> {
    override fun validate(value: T): ValidationResult {
      return this@KCondition.validate(value).let { result ->
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