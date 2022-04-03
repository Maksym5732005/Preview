package com.preview.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.preview.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding, VM: ViewModel> : DaggerFragment() {

    abstract val inflater: (LayoutInflater, ViewGroup?, Boolean) -> T
    abstract val viewModel: VM
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

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

    open fun onEvent(event: Event?) {
        when (event) {
            is NavigationEvent.NavigationId -> findNavController().navigate(event.destinationId)
            is NavigationEvent.NavigationDirection -> findNavController().navigate(event.direction)
            is DebugMessageEvent -> showMessage(event.msg)
        }
    }

    open fun showMessage(
        msg: String,
        actionText: String? = null,
        action: (() -> Any)? = null,
        actionColor: Int = R.color.teal_700,
        length: Int = Snackbar.LENGTH_LONG
    ) {
        Snackbar.make(binding.root, msg, length).apply {
            view.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.white, null))
            view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                ?.setTextColor(ResourcesCompat.getColor(resources, R.color.purple_500, null))
            if (action != null && actionText != null) {
                setAction(actionText) { action.invoke() }
            }
            setActionTextColor(ContextCompat.getColor(this.context, actionColor))
        }.show()
    }
}