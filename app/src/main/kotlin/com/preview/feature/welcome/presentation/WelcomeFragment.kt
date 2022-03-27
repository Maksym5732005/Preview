package com.preview.feature.welcome.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.preview.base.BaseFragment
import com.preview.databinding.FragmentWelcomeBinding as Binding

class WelcomeFragment : BaseFragment<Binding, WelcomeViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding = Binding::inflate
    override val viewModel: WelcomeViewModel by viewModels()
}