package com.skiff2011.fieldvalidator.field

import com.skiff2011.fieldvalidator.condition.Condition
import java.io.Serializable

class SimpleValidateableFieldFactory : ValidateableFieldFactory<ValidateableField<*>> {

  override fun <T : Serializable> createField(
    initialValue: T,
    condition: Condition<T>,
    viewId: Int
  ): ValidateableField<*> = ValidateableField(initialValue, viewId, condition)
}