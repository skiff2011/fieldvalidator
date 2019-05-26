package com.skiff2011.fieldvalidator.bus

import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber
import java.io.Serializable

interface ValidationBus : Serializable {

  fun subscribe(subscriber: ValidationViewSubscriber)

  fun unsubscribe(subscriber: ValidationViewSubscriber)

  fun post(event: ValidationEvent)

}