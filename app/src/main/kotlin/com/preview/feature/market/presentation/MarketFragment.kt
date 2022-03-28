package com.preview.feature.market.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.preview.base.BaseFragment
import com.preview.databinding.FragmentMarketBinding as Binding

class MarketFragment : BaseFragment<Binding, MarketViewModel>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding get() = Binding::inflate
    override val viewModel: MarketViewModel by viewModels()
}