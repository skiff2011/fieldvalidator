package com.skiff2011.fieldvalidator.sample

import androidx.fragment.app.viewModels
import com.skiff2011.fieldvalidator.sample.databinding.FragmentFieldAutoValidatorBinding

class FieldAutoValidatorFragment :
  BaseFragment<FragmentFieldAutoValidatorBinding, FieldAutoValidatorViewModel>() {

  override val viewModel: FieldAutoValidatorViewModel by viewModels()

  override val layoutId: Int = R.layout.fragment_field_auto_validator

  companion object {
    fun getInstance(): FieldAutoValidatorFragment = FieldAutoValidatorFragment()
  }
}