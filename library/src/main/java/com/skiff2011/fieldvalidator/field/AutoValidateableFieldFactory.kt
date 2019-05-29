package com.skiff2011.fieldvalidator.field

import com.skiff2011.fieldvalidator.condition.Condition
import java.io.Serializable

class AutoValidateableFieldFactory : ValidateableFieldFactory<AutoValidateableField<*>> {

  override fun <T : Serializable> createField(
    initialValue: T,
    condition: Condition<T>,
    viewId: Int
  ): AutoValidateableField<*> = AutoValidateableField(initialValue, viewId, condition)
}