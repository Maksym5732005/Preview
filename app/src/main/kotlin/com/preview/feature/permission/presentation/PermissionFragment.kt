package com.preview.feature.permission.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.preview.R
import com.preview.base.extensions.observe
import com.preview.base.presentation.BaseFragment
import com.preview.base.presentation.Event
import com.preview.databinding.FragmentPermissionBinding as Binding

class PermissionFragment : BaseFragment<Binding, PermissionViewModel>(),
    PermissionDelegate by PermissionDelegateImpl() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding = Binding::inflate
    override val viewModel: PermissionViewModel by viewModels { viewModelFactory }

    override fun PermissionViewModel.initViewModel() {
        registerPermissionDelegate()
        observe(event, ::onEvent)
        observe(areFeaturesBlocked, ::renderBlockedFeatures)
    }

    override fun onEvent(event: Event?) {
        if (event is PermissionEvent) {
            checkPermission(event.perm) { isGranted -> viewModel.onPermissionResult(event.perm, isGranted) }
        } else {
            super.onEvent(event)
        }
    }

    private fun renderBlockedFeatures(areBlocked: Boolean?) {
        areBlocked?.let { blocked ->
            binding.textDescription.apply {
                isVisible = true
                val stringRes = if (blocked) {
                    R.string.permission_description_not_granted
                } else {
                    R.string.permission_description_granted
                }
                text = getString(stringRes)
            }
            binding.imagePermission.apply {
                isVisible = true
                setImageResource(if (blocked) R.drawable.img_not_granted else R.drawable.img_granted)
            }
        }
    }
}