package com.skiff2011.fieldvalidator.sample

import android.app.Application
import androidx.databinding.Bindable
import com.skiff2011.fieldvalidator.FieldAutoValidator
import com.skiff2011.fieldvalidator.ValidationCallback

class FieldAutoValidatorViewModel(
  application: Application
) : BaseViewModel(application) {

  val validator = FieldAutoValidator(validationCallback = ValidationCallback { value ->
    valid = value
  })

  var string0: String by validator.validateable(
    "",
    R.id.til1,
    EmptyStringCondition(),
    TextInputLayoutViewState()
  )

  var string1: String by validator.validateable(
    "",
    R.id.til2,
    EmptyStringCondition(),
    TextInputLayoutViewState()
  )

  @get:Bindable var checked0: Boolean = false
    set(value) {
      field = value
      if (value) {
        validator.enableAutoValidation(listOf(R.id.til1))
      } else {
        validator.disableAutoValidation(listOf(R.id.til1))
      }
    }

  @get:Bindable var checked1: Boolean = false
    set(value) {
      field = value
      if (value) {
        validator.enableAutoValidation(listOf(R.id.til2))
      } else {
        validator.disableAutoValidation(listOf(R.id.til2))
      }
    }

  @get:Bindable var valid: Boolean = validator.isValid
    private set(value) {
      field = value
      notifyPropertyChanged(BR.valid)
    }
}