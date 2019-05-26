package com.skiff2011.fieldvalidator.field

import java.io.Serializable

interface ValidateableFieldFactory<F : ValidateableField<*>> : Serializable {
  fun createField(): F
}
