package com.skiff2011.fieldvalidator.bus

import android.view.View
import com.skiff2011.fieldvalidator.view.GreenRobotValidationViewSubscriber
import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber

class GreenRobotValidationBusFactory : ValidationBusFactory {

  override fun createSubscriber(view: View): ValidationViewSubscriber =
    GreenRobotValidationViewSubscriber(view)

  override fun createBus(): ValidationBus = GreenRobotValidationBus()
}