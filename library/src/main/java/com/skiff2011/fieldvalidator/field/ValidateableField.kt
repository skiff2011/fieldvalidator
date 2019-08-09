package com.skiff2011.fieldvalidator.field

import com.skiff2011.fieldvalidator.Subscribeable
import com.skiff2011.fieldvalidator.bus.ValidationBus
import com.skiff2011.fieldvalidator.condition.KCondition
import com.skiff2011.fieldvalidator.condition.Valid
import com.skiff2011.fieldvalidator.condition.ValidationResult
import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber
import com.skiff2011.fieldvalidator.view.ViewEventProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ValidateableField<T : Any?>(
  initialValue: T,
  val viewId: Int,
  private val condition: KCondition<T>,
  private val validationCallback: FieldValidationCallback?,
  private val bus: ValidationBus,
  private val eventProvider: ViewEventProvider<*>,
  private val onValueChanged: ((T) -> Unit)?
) : ReadWriteProperty<Any?, T>, Subscribeable<ValidationViewSubscriber> {

  private var value: T = initialValue

  var autoValidateable: Boolean = false
    set(value) {
      if (value) {
        validate()
        field = value
      } else {
        field = value
        validationResult = Valid
      }
    }

  var validationResult: ValidationResult = Valid
    set(value) {
      field = value
      displayViewState(value)
      if (autoValidateable) {
        validationCallback?.onValidationResultChanged(viewId, value)
      }
    }

  override fun getValue(
    thisRef: Any?,
    property: KProperty<*>
  ): T = this.value

  override fun setValue(
    thisRef: Any?,
    property: KProperty<*>,
    value: T
  ) {
    this.value = value
    if (autoValidateable) {
      validationResult = condition.validate(value)
    }
    onValueChanged?.invoke(value)
  }

  fun validate(): ValidationResult = if (!autoValidateable) {
    condition.validate(value).also { result ->
      validationResult = result
    }
  } else {
    validationResult
  }

  private fun displayViewState(result: ValidationResult) {
    bus.post(eventProvider.generateViewStateEvent(result))
  }

  override fun subscribe(subscriber: ValidationViewSubscriber) {
    bus.subscribe(subscriber)
  }

  override fun unsubscribe(subscriber: ValidationViewSubscriber) {
    bus.unsubscribe(subscriber)
  }

}