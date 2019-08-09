package com.skiff2011.fieldvalidator.bus

import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber
import org.greenrobot.eventbus.EventBus

class GreenRobotValidationBus : ValidationBus() {

  private val bus = EventBus.builder().build()

  override fun postBusEvent(event: ValidationEvent) {
    bus.post(event)
  }

  override fun subscribeImpl(subscriber: ValidationViewSubscriber) {
    bus.register(subscriber)
  }

  override fun unSubscribeImpl(subscriber: ValidationViewSubscriber) {
    bus.unregister(subscriber)
  }
}