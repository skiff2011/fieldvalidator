package com.skiff2011.fieldvalidator

import java.io.Serializable

interface ValidationCallback : Serializable {
  fun onValidStatusChanged(isValid: Boolean)
}