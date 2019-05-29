package com.skiff2011.fieldvalidator.field

import android.support.annotation.CallSuper
import com.skiff2011.fieldvalidator.condition.Condition
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class ValidateableField<T : Serializable>(
  initialValue: T,
  val viewId: Int,
  private val condition: Condition<T>
) : ReadWriteProperty<Any?, T>, Serializable {

  private var value: T = initialValue

  @CallSuper
  override fun getValue(thisRef: Any?, property: KProperty<*>): T = this.value

  @CallSuper
  override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    this.value = value
  }

  open fun validate(): String? = this.condition.validate(value)
}