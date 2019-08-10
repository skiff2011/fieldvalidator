package com.skiff2011.fieldvalidator.condition

class EmailCondition(private val error: String) : RegexCondition(
  error, android.util.Patterns.EMAIL_ADDRESS.toRegex()
)