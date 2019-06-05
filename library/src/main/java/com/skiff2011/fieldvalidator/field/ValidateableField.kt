package com.skiff2011.fieldvalidator.field

import android.support.annotation.CallSuper
import com.skiff2011.fieldvalidator.condition.Condition
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class ValidateableField<T : Serializable?>(
  initialValue: T,
  val viewId: Int,
  condition: Condition<T>,
  private val onValueChanged: ((T) -> Unit)? = null
) : ReadWriteProperty<Any?, T>, Serializable {

  private var value: T = initialValue

  var condition: Condition<T> = condition
    set(value) {
      if (field != value) {
        field = value
        validate()
      }
    }

  @CallSuper
  override fun getValue(
    thisRef: Any?,
    property: KProperty<*>
  ): T = this.value

  @CallSuper
  override fun setValue(
    thisRef: Any?,
    property: KProperty<*>,
    value: T
  ) {

    this.value = value
    onValueChanged?.invoke(value)
  }

  open fun validate(): String? = this.condition.validate(value)
}