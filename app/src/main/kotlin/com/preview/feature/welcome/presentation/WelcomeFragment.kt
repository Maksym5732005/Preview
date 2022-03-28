package com.preview.feature.welcome.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.preview.base.BaseFragment
import com.preview.base.observe
import com.preview.databinding.FragmentWelcomeBinding as Binding

internal class WelcomeFragment : BaseFragment<Binding, WelcomeViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding = Binding::inflate
    override val viewModel: WelcomeViewModel by viewModels()

    private val controller = WelcomeEpoxyController()

    override fun Binding.initViews() {
        viewEpoxy.layoutManager = LinearLayoutManager(requireContext())
        viewEpoxy.setController(controller)
    }

    override fun WelcomeViewModel.initViewModel() {
        observe(items) { controller.setData(it) }
    }
}