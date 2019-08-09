package com.skiff2011.fieldvalidator.sample

import com.google.android.material.textfield.TextInputLayout
import com.skiff2011.fieldvalidator.view.ValidationViewState

class TextInputLayoutViewState : ValidationViewState<TextInputLayout> {
  override fun showError(error: String, view: TextInputLayout) {
    view.error = error
  }

  override fun hideError(view: TextInputLayout) {
    view.error = null
  }
}