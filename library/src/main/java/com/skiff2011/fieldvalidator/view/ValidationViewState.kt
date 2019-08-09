package com.skiff2011.fieldvalidator.view

import android.view.View

interface ValidationViewState<T : View> {

  fun showError(error: String, view: T)

  fun hideError(view: T)
}