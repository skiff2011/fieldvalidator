package com.skiff2011.fieldvalidator.sample

import androidx.fragment.app.viewModels
import com.skiff2011.fieldvalidator.sample.databinding.FragmentFieldValidatorBinding

class FieldValidatorFragment :
  BaseFragment<FragmentFieldValidatorBinding, FieldValidatorViewModel>() {

  override val viewModel: FieldValidatorViewModel by viewModels()

  override val layoutId: Int = R.layout.fragment_field_validator

  companion object {
    fun getInstance(): FieldValidatorFragment = FieldValidatorFragment()
  }
}