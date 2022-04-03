package com.preview.feature.market.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.preview.base.extensions.observe
import com.preview.base.presentation.BaseFragment
import com.preview.feature.market.presentation.epoxy.MarketEpoxyController
import com.preview.databinding.FragmentMarketBinding as Binding

class MarketFragment : BaseFragment<Binding, MarketViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding get() = Binding::inflate
    override val viewModel: MarketViewModel by viewModels { viewModelFactory }

    private var controller: MarketEpoxyController? = null

    override fun Binding.initViews() {
        controller = MarketEpoxyController(viewModel).also(viewEpoxy::setController)
        swipe.setOnRefreshListener(viewModel::refreshRequested)
    }

    override fun MarketViewModel.initViewModel() {
        observe(event, ::onEvent)
        observe(viewState) { controller?.setData(it) }
        observe(loadingState, ::renderRefresh)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        controller = null
    }

    private fun renderRefresh(isLoading: Boolean?) {
        if (binding.swipe.isRefreshing && isLoading == false) {
            binding.swipe.isRefreshing = false
        }
    }
}