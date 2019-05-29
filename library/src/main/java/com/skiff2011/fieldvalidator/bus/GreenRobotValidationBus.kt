package com.skiff2011.fieldvalidator.bus

import com.skiff2011.fieldvalidator.view.ValidationViewSubscriber
import org.greenrobot.eventbus.EventBus

class GreenRobotValidationBus : ValidationBus() {

  @Transient private var bus: EventBus? = null
    get() {
      if (field == null) {
        field = EventBus.builder().build()
      }
      return field
    }

  override fun postBusEvent(event: ValidationEvent) {
    bus!!.post(event)
  }

  override fun subscribeSubscriber(subscriber: ValidationViewSubscriber) {
    bus!!.register(subscriber)
  }

  override fun unsubscribeSubscriber(subscriber: ValidationViewSubscriber) {
    bus!!.unregister(subscriber)
  }
}