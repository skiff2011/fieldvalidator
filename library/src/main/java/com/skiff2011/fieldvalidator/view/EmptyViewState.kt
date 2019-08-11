package com.skiff2011.fieldvalidator.view

import android.view.View

class EmptyViewState<T : View> : ValidationViewState<T> {
  override fun showError(error: String, view: T) {

  }

  override fun hideError(view: T) {

  }
}