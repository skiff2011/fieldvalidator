package com.skiff2011.fieldvalidator.sample

import android.app.Application
import android.widget.Toast
import androidx.databinding.Bindable
import com.skiff2011.fieldvalidator.FieldValidator

class FieldValidatorViewModel(
  application: Application
) : BaseViewModel(application) {

  val validator = FieldValidator()

  private val idsToValidate: MutableSet<Int> = mutableSetOf(R.id.til1, R.id.til2)

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

  @get:Bindable var checked0: Boolean = true
    set(value) {
      field = value
      if (value) {
        idsToValidate.add(R.id.til1)
      } else {
        idsToValidate.remove(R.id.til1)
      }
    }

  @get:Bindable var checked1: Boolean = true
    set(value) {
      field = value
      if (value) {
        idsToValidate.add(R.id.til2)
      } else {
        idsToValidate.remove(R.id.til2)
      }
    }

  fun onValidateSelectedClicked() {
    Toast.makeText(
      getApplication(),
      "Validation status = ${validator.validate(idsToValidate.toList())} ids = $idsToValidate",
      Toast.LENGTH_SHORT
    )
      .show()
  }

  fun onValidateAllClicked() {
    Toast.makeText(
      getApplication(),
      "Validation status = ${validator.validate()}", Toast.LENGTH_SHORT
    )
      .show()
  }
}