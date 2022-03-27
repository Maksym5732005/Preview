package com.preview.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding, VM: ViewModel> : Fragment() {

    abstract val inflater: (LayoutInflater, ViewGroup?, Boolean) -> T
    abstract val viewModel: VM

    protected open fun T.initViews() = Unit
    protected open fun VM.initViewModel() = Unit

    private var _binding: T? = null
    protected val binding: T
        get() = checkNotNull(_binding) {
            "${this.javaClass.simpleName}: This property is only valid between onCreateView and onDestroyView."
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initViews()
        viewModel.initViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}