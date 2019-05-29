package com.skiff2011.fieldvalidator.condition

import java.io.Serializable

interface Condition<T : Serializable?> : Serializable {
  //return null if valid
  fun validate(value: T): String?

  fun thenValidate(nextCondition: Condition<T>): Condition<T> = object :
    Condition<T> {
    override fun validate(value: T): String? {
      return this@Condition.validate(value) ?: nextCondition.validate(value)
    }

  }
}