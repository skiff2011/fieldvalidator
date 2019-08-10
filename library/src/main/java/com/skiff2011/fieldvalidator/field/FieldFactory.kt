package com.skiff2011.fieldvalidator.field

import com.skiff2011.fieldvalidator.bus.ValidationBus
import com.skiff2011.fieldvalidator.condition.Condition
import com.skiff2011.fieldvalidator.view.ViewEventProvider

interface FieldFactory {
  fun <T : Any?> createField(
    initialValue: T,
    condition: Condition<T>,
    viewId: Int,
    callback: FieldValidationCallback?,
    bus: ValidationBus,
    eventProvider: ViewEventProvider<*>,
    onValueChanged: ((T) -> Unit)? = null
  ): ValidateableField<T>
}