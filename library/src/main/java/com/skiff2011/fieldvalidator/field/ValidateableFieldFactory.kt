package com.skiff2011.fieldvalidator.field

import com.skiff2011.fieldvalidator.condition.Condition
import java.io.Serializable

interface ValidateableFieldFactory<F : ValidateableField<*>> : Serializable {
  fun <T : Serializable?> createField(
    initialValue: T,
    condition: Condition<T>,
    viewId: Int,
    onValueChanged: ((T) -> Unit)? = null
  ): F
}
