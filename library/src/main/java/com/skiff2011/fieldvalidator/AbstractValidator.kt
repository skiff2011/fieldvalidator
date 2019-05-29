package com.skiff2011.fieldvalidator

import android.view.View
import com.skiff2011.fieldvalidator.bus.ValidationBus
import com.skiff2011.fieldvalidator.bus.ValidationBusFactory
import com.skiff2011.fieldvalidator.condition.Condition
import com.skiff2011.fieldvalidator.field.ValidateableField
import com.skiff2011.fieldvalidator.field.ValidateableFieldFactory
import com.skiff2011.fieldvalidator.view.ValidationViewState
import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber
import com.skiff2011.fieldvalidator.view.ViewEventProvider
import java.io.Serializable
import kotlin.properties.ReadWriteProperty

abstract class AbstractValidator<F : ValidateableField<*>>(
  validationBusFactory: ValidationBusFactory,
  validateableFieldFactory: ValidateableFieldFactory<F>
) : ValidationBusFactory by validationBusFactory,
  ValidateableFieldFactory<F> by validateableFieldFactory,
  Serializable {

  private val eventProviders: HashMap<Int, ViewEventProvider<*>> = HashMap()

  private val buses: HashMap<Int, ValidationBus> = HashMap()

  private val fields: HashMap<Int, F> = HashMap()

  inline fun <T : Serializable, reified V : View> validateable(
    initialValue: T,
    viewId: Int,
    viewState: ValidationViewState<V>,
    condition: Condition<T>
  ): ReadWriteProperty<Any?, T> {
    val property = createField(initialValue, condition, viewId)
    addField(viewId, property, ViewEventProvider(viewState, V::class.java))
    return property as ReadWriteProperty<Any?, T>
  }

  open fun validate(): Boolean {
    var isValid = true
    fields.entries.forEach { entry ->
      val error = entry.value.validate()
      applyViewState(entry.key, error)
      isValid = isValid && (error == null)
    }
    return isValid
  }

  fun addField(viewId: Int, field: F, viewEventProvider: ViewEventProvider<*>) {
    buses[viewId] = createBus()
    fields[viewId] = field
    eventProviders[viewId] = viewEventProvider
  }

  fun removeField(viewId: Int) {
    eventProviders.remove(viewId)
    buses.remove(viewId)
    fields.remove(viewId)
  }

  fun getField(viewId: Int): F? = fields[viewId]

  fun applyViewState(viewId: Int, error: String?) {
    val eventProvider = eventProviders[viewId]
    val bus = buses[viewId]
    if (eventProvider != null && bus != null) {
      bus.post(eventProvider.generateViewStateEvent(error))
    } else {
      //TODO throw exception no field with this viewId
    }
  }

  fun subscribe(subscriber: ValidationViewSubscriber) {
    val bus = buses[subscriber.getId()]
    if (bus != null) {
      bus.subscribe(subscriber)
    } else {
      //TODO throw exception
    }
  }

  //TODO clone
  fun fields(): HashMap<Int, F> = fields

  fun unsubscribe(subscriber: ValidationViewSubscriber) {
    val bus = buses[subscriber.getId()]
    if (bus != null) {
      bus.unsubscribe(subscriber)
    } else {
      //TODO throw exception
    }
  }
}