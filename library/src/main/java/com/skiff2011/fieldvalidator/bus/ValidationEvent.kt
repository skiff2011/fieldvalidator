package com.skiff2011.fieldvalidator.bus

import android.view.View

interface ValidationEvent {
  fun performAction(view: View)
}