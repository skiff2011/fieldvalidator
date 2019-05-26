package com.skiff2011.fieldvalidator.view

import android.view.View
import java.io.Serializable

interface ValidationViewState<T : View> : Serializable {

  fun showError(error: String, view: T)

  fun hideError(view: T)
}