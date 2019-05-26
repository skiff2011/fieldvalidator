package com.skiff2011.fieldvalidator

import android.view.View
import com.skiff2011.fieldvalidator.bus.ValidationBus
import com.skiff2011.fieldvalidator.bus.ValidationBusFactory
import com.skiff2011.fieldvalidator.condition.Condition
import com.skiff2011.fieldvalidator.field.ValidateableField
import com.skiff2011.fieldvalidator.field.ValidateableFieldFactory
import com.skiff2011.fieldvalidator.view.ValidationViewState
import java.io.Serializable
import kotlin.properties.ReadWriteProperty

abstract class AbstractValidator<F : ValidateableField<*>>(
  val validationBusFactory: ValidationBusFactory,
  val validateableFieldFactory: ValidateableFieldFactory<F>
) : ValidationBus, ValidationBusFactory, Serializable {

  inline fun <T : Serializable, reified V : View> validateable(
    initialValue: T,
    viewId: Int,
    viewState: ValidationViewState<V>,
    condition: Condition<T>
  ): ReadWriteProperty<Any?, T> {

  }

  open fun isValid(): Boolean {

  }

  fun addField(viewId: Int, field: F) {

  }

  fun removeField(viewId: Int) {

  }
}