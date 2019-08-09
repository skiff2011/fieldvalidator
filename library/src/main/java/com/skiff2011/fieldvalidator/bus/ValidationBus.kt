package com.skiff2011.fieldvalidator.bus

import com.skiff2011.fieldvalidator.Subscribeable
import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber

abstract class ValidationBus : Subscribeable<ValidationViewSubscriber> {

  private var lastEvent: ValidationEvent? = null

  override fun subscribe(subscriber: ValidationViewSubscriber) {
    subscribeImpl(subscriber)
    lastEvent?.let { event ->
      post(event)
    }
  }

  override fun unsubscribe(subscriber: ValidationViewSubscriber) {
    unSubscribeImpl(subscriber)
  }

  fun post(event: ValidationEvent) {
    lastEvent = event
    postBusEvent(event)
  }

  protected abstract fun postBusEvent(event: ValidationEvent)

  protected abstract fun subscribeImpl(subscriber: ValidationViewSubscriber)

  protected abstract fun unSubscribeImpl(subscriber: ValidationViewSubscriber)

}