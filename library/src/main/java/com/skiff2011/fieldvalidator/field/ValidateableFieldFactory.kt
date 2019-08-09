package com.skiff2011.fieldvalidator.field

import com.skiff2011.fieldvalidator.bus.ValidationBus
import com.skiff2011.fieldvalidator.condition.KCondition
import com.skiff2011.fieldvalidator.view.ViewEventProvider

class ValidateableFieldFactory : FieldFactory {
  override fun <T> createField(
    initialValue: T,
    condition: KCondition<T>,
    viewId: Int,
    callback: FieldValidationCallback?,
    bus: ValidationBus,
    eventProvider: ViewEventProvider<*>,
    onValueChanged: ((T) -> Unit)?
  ): ValidateableField<T> = ValidateableField(
    initialValue,
    viewId,
    condition,
    callback,
    bus,
    eventProvider,
    onValueChanged
  )
}