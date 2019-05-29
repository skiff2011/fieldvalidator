package com.skiff2011.fieldvalidator.bus

import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber
import java.io.Serializable

abstract class ValidationBus : Serializable {

  private var lastEvent: ValidationEvent? = null

  fun subscribe(subscriber: ValidationViewSubscriber) {
    subscribeSubscriber(subscriber)
    lastEvent?.let { event ->
      post(event)
    }
  }

  fun unsubscribe(subscriber: ValidationViewSubscriber) {
    unsubscribeSubscriber(subscriber)
  }

  fun post(event: ValidationEvent) {
    lastEvent = event
    postBusEvent(event)
  }

  protected abstract fun postBusEvent(event: ValidationEvent)

  protected abstract fun subscribeSubscriber(subscriber: ValidationViewSubscriber)

  protected abstract fun unsubscribeSubscriber(subscriber: ValidationViewSubscriber)

}