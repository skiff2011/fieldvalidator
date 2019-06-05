package com.skiff2011.fieldvalidator.bus

import android.view.View
import java.io.Serializable

interface ValidationEvent : Serializable {
  fun performAction(view: View)
}