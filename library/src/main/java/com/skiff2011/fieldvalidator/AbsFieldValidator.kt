package com.skiff2011.fieldvalidator

import android.view.View
import com.skiff2011.fieldvalidator.bus.ValidationBusFactory
import com.skiff2011.fieldvalidator.condition.Condition
import com.skiff2011.fieldvalidator.condition.ValidationResult
import com.skiff2011.fieldvalidator.field.FieldFactory
import com.skiff2011.fieldvalidator.field.FieldValidationCallback
import com.skiff2011.fieldvalidator.field.ValidateableField
import com.skiff2011.fieldvalidator.view.EmptyViewState
import com.skiff2011.fieldvalidator.view.ValidationViewState
import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber
import com.skiff2011.fieldvalidator.view.ViewEventProvider
import kotlin.properties.ReadWriteProperty

abstract class AbsFieldValidator(
  busFactory: ValidationBusFactory,
  fieldFactory: FieldFactory
) : Subscribeable<ValidationViewSubscriber>,
  ValidationBusFactory by busFactory,
  FieldFactory by fieldFactory {

  private val fields: MutableMap<Int, ValidateableField<*>> = HashMap()

  inline fun <T : Any?, reified V : View> validateable(
    initialValue: T,
    viewId: Int,
    condition: Condition<T>,
    viewState: ValidationViewState<V> = EmptyViewState(),
    noinline onValueChanged: ((T) -> Unit)? = null
  ): ReadWriteProperty<Any?, T> {
    val field = createField(
      initialValue,
      condition,
      viewId,
      this as? FieldValidationCallback,
      createBus(),
      ViewEventProvider(viewState, V::class.java),
      onValueChanged
    )
    addField(viewId, field)
    return field
  }

  fun addField(
    viewId: Int,
    field: ValidateableField<*>
  ) {
    fields[viewId] = field
  }

  protected fun fields(): Map<Int, ValidateableField<*>> = fields.toMap()

  override fun subscribe(subscriber: ValidationViewSubscriber) {
    fields[subscriber.getId()]?.subscribe(subscriber)
  }

  override fun unsubscribe(subscriber: ValidationViewSubscriber) {
    fields[subscriber.getId()]?.unsubscribe(subscriber)
  }

  fun setFieldValidationResult(viewId: Int, result: ValidationResult) {
    fields[viewId]?.validationResult = result
  }

}