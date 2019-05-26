package com.skiff2011.fieldvalidator.bus

import android.view.View
import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber
import java.io.Serializable

interface ValidationBusFactory : Serializable {

  fun createBus(): ValidationBus

  fun createSubscriber(view: View): ValidationViewSubscriber
}