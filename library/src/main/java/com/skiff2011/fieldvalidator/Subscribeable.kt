package com.skiff2011.fieldvalidator

interface Subscribeable<T> {
  fun subscribe(subscriber: T)

  fun unsubscribe(subscriber: T)
}