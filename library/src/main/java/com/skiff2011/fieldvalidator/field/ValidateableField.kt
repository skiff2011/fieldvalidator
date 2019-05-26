package com.skiff2011.fieldvalidator.field

import android.support.annotation.CallSuper
import com.skiff2011.fieldvalidator.condition.Condition
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class ValidateableField<T : Serializable>(
  initialValue: T,
  private val condition: Condition<T>
//  private val viewState: ValidationViewState<V>,
//  private val viewClass: Class<V>
) : ReadWriteProperty<Any?, T>, Serializable {

  private var value: T = initialValue

  @CallSuper
  override fun getValue(thisRef: Any?, property: KProperty<*>): T = this.value

  @CallSuper
  override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    this.value = value
  }

  fun validate(): String? = this.condition.validate(value)

  /*fun generateViewStateEvent(error: String?): ValidationEvent = object : ValidationEvent {
    override fun performAction(view: View) {
      if (!viewClass.isInstance(view)) {
        throw TypeCastException("ValidationViewState type differs from the type of view")
      }
      val validateableView = viewClass.cast(view)!!
      if (error == null) {
        viewState.hideError(validateableView)
      } else {
        viewState.showError(error, validateableView)
      }
    }
  }*/
}