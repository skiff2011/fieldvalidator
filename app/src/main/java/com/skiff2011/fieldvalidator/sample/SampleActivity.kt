package com.skiff2011.fieldvalidator.sample

import android.databinding.Bindable
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.ObservableBoolean
import android.databinding.PropertyChangeRegistry
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.skiff2011.fieldvalidator.OptionalAutoValidator
import com.skiff2011.fieldvalidator.ValidationCallback
import com.skiff2011.fieldvalidator.bus.GreenRobotValidationBusFactory
import com.skiff2011.fieldvalidator.sample.databinding.ActivitySampleBinding

class SampleActivity : AppCompatActivity(), Observable, ValidationCallback {

  val enabled = ObservableBoolean(true)

  override fun onValidStatusChanged(isValid: Boolean) {
    enabled.set(isValid)
  }

  val validator = OptionalAutoValidator(GreenRobotValidationBusFactory())

  @get:Bindable var string1: String by validator.validateable(
    "",
    R.id.til1,
    TextInputLayoutViewState(),
    EmptyStringCondition()
  )

  @get:Bindable var string2: String by validator.validateable(
    "",
    R.id.til2,
    TextInputLayoutViewState(),
    EmptyStringCondition()
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding =
      DataBindingUtil.setContentView<ActivitySampleBinding>(this, R.layout.activity_sample)
    binding.setVariable(BR.activity, this)
    binding.executePendingBindings()
    validator.setValidationCallback(this)
    validator.enableAll()
    validator.disableAll()
  }

  fun onClick() {
    Toast.makeText(this, "${validator.validate()}", Toast.LENGTH_LONG).show()
  }

  @Transient private var mCallbacks: PropertyChangeRegistry? = null

  override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
    synchronized(this) {
      if (mCallbacks == null) {
        mCallbacks = PropertyChangeRegistry()
      }
    }
    mCallbacks!!.add(callback)
  }

  override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
    synchronized(this) {
      if (mCallbacks == null) {
        return
      }
    }
    mCallbacks!!.remove(callback)
  }

  /**
   * Notifies listeners that all properties of this instance have changed.
   */
  fun notifyChange() {
    synchronized(this) {
      if (mCallbacks == null) {
        return
      }
    }
    mCallbacks!!.notifyCallbacks(this, 0, null)
  }

  /**
   * Notifies listeners that a specific property has changed. The getter for the property
   * that changes should be marked with [Bindable] to generate a field in
   * `BR` to be used as `fieldId`.
   *
   * @param fieldId The generated BR id for the Bindable field.
   */
  fun notifyPropertyChanged(fieldId: Int) {
    synchronized(this) {
      if (mCallbacks == null) {
        return
      }
    }
    mCallbacks!!.notifyCallbacks(this, fieldId, null)
  }
}
