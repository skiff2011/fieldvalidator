package com.skiff2011.fieldvalidator

import java.io.Serializable

interface FieldValidationCallback : Serializable {
  fun onErrorChanged(viewId: Int, error: String?)
}