package com.preview.feature.welcome.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.preview.base.BaseFragment
import com.preview.base.extensions.observe
import com.preview.databinding.FragmentWelcomeBinding as Binding

class WelcomeFragment : BaseFragment<Binding, WelcomeViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding = Binding::inflate
    override val viewModel: WelcomeViewModel by viewModels { viewModelFactory }

    private var controller: WelcomeEpoxyController? = null

    override fun Binding.initViews() {
        viewEpoxy.layoutManager = LinearLayoutManager(requireContext())
        controller = WelcomeEpoxyController(
            itemClickListener = (viewModel::itemRequested)
        ).also (viewEpoxy::setController)
    }

    override fun WelcomeViewModel.initViewModel() {
        observe(items) { controller?.setData(it) }
        observe(event, ::onEvent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        controller = null
    }
}