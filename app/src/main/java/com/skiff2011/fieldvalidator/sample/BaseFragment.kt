package com.skiff2011.fieldvalidator.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

  abstract val layoutId: Int

  abstract val viewModel: V

  lateinit var binding: T

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    binding.lifecycleOwner = this
    binding.setVariable(BR.viewModel, viewModel)
    return binding.root
  }
}