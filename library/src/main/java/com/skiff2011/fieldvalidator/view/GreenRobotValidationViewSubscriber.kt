package com.skiff2011.fieldvalidator.view

import android.view.View
import com.skiff2011.fieldvalidator.bus.ValidationEvent
import org.greenrobot.eventbus.Subscribe

class GreenRobotValidationViewSubscriber(view: View) : ValidationViewSubscriber(view) {

  @Subscribe
  override fun onValidationEventReceived(validationEvent: ValidationEvent) {
    super.onValidationEventReceived(validationEvent)
  }
}